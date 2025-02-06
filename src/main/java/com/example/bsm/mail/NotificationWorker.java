package com.example.bsm.mail;

import com.example.bsm.entity.*;
import com.example.bsm.repository.BloodBankRepository;
import com.example.bsm.repository.DonationRequestRepository;
import com.example.bsm.repository.HospitalRepository;
import com.example.bsm.repository.UserRepository;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.awt.SystemColor.text;

@AllArgsConstructor
@Component
public class NotificationWorker {
    private final UserRepository userRepository;
    private final DonationRequestRepository donationreqrepository;
    private final HospitalRepository hospitalRepository;
    private final BloodBankRepository bankRepository;
    private final MailService mailService;


    @Scheduled(fixedRate = 60000)
    public void sendBloodDonationRequestNotification() throws MessagingException {
        List<DonationRequest> donationRequests = donationreqrepository.findByRequestCompleted(false);

        for(DonationRequest req : donationRequests){
           Oragnization org = this.findOrganizationDetails(req);
           this.sendDonationRequestNotification(org,req);
        }
    }
    private void sendDonationRequestNotification(Oragnization org,DonationRequest req) throws MessagingException {
        List<User> users = userRepository.findAllByAvailableCityInAndBloodGroupIn(req.getBloodGroupList(),req.getCities());
        for (User  user : users){
            Map<String,Object> variables = new HashMap<>();
            variables.put("organizationName",org.getOrgName());
            variables.put("organizationAddress",org.getOrgAddress());
            variables.put("bloodGroup", user.getBloodGroup().name());
            variables.put("surveyLink","");
            variables.put("donateLink","");

           String text = mailService.genratecontent("DoneationRequest",variables);
            mailService.sendmail(user.getEmail(), "Urgent:BloodRequired",text);
        }
    }
    @Setter
    @Getter
    public static class Oragnization{
        private String orgName;
        private String orgAddress;
    }
    private static String getOrganizationAddress(Address address) {
        return address.getAddressline() +" \n  "+ address.getArea() +
                " \n  "+ address.getCity() +" \n  "+ address.getState()
                +" \n  "+ address.getCountry() +" \n  "+ address.getPincode();
    }
    private Oragnization findOrganizationDetails(DonationRequest request){
        Oragnization org = new Oragnization();
        switch (request.getOrgType()){
            case HOPITAL -> {
                Optional<Hospital> optional = hospitalRepository.findByDonationRequest(request);
                Hospital hospital=optional.get();
                org.orgName=hospital.getName();
                Address addresses = hospital.getAddresses();
                org.orgAddress = getOrganizationAddress(new Address());

            }
            case BLOODBANK -> {
                Optional<BloodBank> optional = bankRepository.findByDonationRequest(request);
                BloodBank bloodBank=optional.get();
                org.orgName=bloodBank.getBankName();
                Address address=bloodBank.getAddress();

            }
        }
        return org;
    }
}
