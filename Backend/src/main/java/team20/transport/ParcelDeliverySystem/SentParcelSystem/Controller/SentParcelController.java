package team20.transport.ParcelDeliverySystem.SentParcelSystem.Controller;


import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team20.transport.ParcelDeliverySystem.Entity.Station;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Entity.Packaging;
import team20.transport.ParcelDeliverySystem.Repository.StationRepository;
import team20.transport.ParcelDeliverySystem.SentParcelSystem.Entity.SentParcel;
import team20.transport.ParcelDeliverySystem.SentParcelSystem.Entity.SentTime;
import team20.transport.ParcelDeliverySystem.SentParcelSystem.Repository.SentParcelRepository;
import team20.transport.ParcelDeliverySystem.SentParcelSystem.Repository.SentTimeRepository;
import team20.transport.ParcelDeliverySystem.PackagingSystem.Repository.PackagingRepository;
import java.util.Map;

@RestController
@RequestMapping("/team20")
@CrossOrigin(origins = {"*"})
public class SentParcelController {
    @Autowired
    PackagingRepository packagingRepository;
    @Autowired
    StationRepository stationRepository;
    @Autowired
    SentParcelRepository sentParcelRepository;
    @Autowired
    SentTimeRepository sentTimeRepository;

    @GetMapping("/findBySentParcelId/{id}")
    public JSONObject findBySentParcelId(@PathVariable Long id){
        SentParcel x = sentParcelRepository.findById(id).get();
        JSONObject y = new JSONObject();
        y.put("id",x.getId());
        y.put("packaging",x.getPackaging());
        y.put("atOriginStation",x.getAtOriginStation());
        y.put("atArriveStation",x.getAtArriveStation());
        y.put("senttime",x.getSenttime());

        return y;

    }


    @PostMapping("/addSentParcel")
    public SentParcel addSentParcel(@RequestBody Map<String,Long> allParams){

        Packaging ofPackage = packagingRepository.findById(Long.valueOf(allParams.get("packageId"))).get();
        Station atOriginStation = stationRepository.findById(allParams.get("originStationId")).get();
        Station atArriveStation = stationRepository.findById(allParams.get("arriveStationId")).get();
        SentTime toParcel = sentTimeRepository.findById(allParams.get("sentTimeId")).get();

        SentParcel newSentParcel = new SentParcel();
        newSentParcel.setPackaging(ofPackage);
        newSentParcel.setAtOriginStation(atOriginStation);
        newSentParcel.setAtArriveStation(atArriveStation);
        newSentParcel.setSenttime(toParcel);

        return sentParcelRepository.save(newSentParcel);
    }
}
