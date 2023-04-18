package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping(value = "/api/test", method = RequestMethod.GET)
public class Api {

    List<Hat> hatList;

    public Api() {
        this.hatList = new ArrayList<Hat>(){};
        this.hatList.add(new Hat(1,"PierwszaCzapka",30, LocalDate.now()));
        this.hatList.add(new Hat(2,"DrugaCzapka",40, LocalDate.now()));
        this.hatList.add(new Hat(3,"TrzeciaCzapka",50, LocalDate.now()));
    }

    @GetMapping("/all")
   public List<Hat> getHatList(){
        return this.hatList;
    }
    @PostMapping
    public boolean addHat(@RequestBody Hat hat ){
        this.hatList.add(hat);
        return this.hatList.contains(hat);

    }


    @GetMapping
    public Optional<Hat> getHatById(@RequestParam int id){
        return this.hatList
                .stream()
                .filter(hat -> hat.getId()==id)
                .findFirst();
    }
    @PutMapping
    public void putHat(@RequestBody Hat hat){
        for (Hat x:this.hatList) {
            if(x.getId()==hat.getId()){
                this.hatList.remove(x);
                this.hatList.add(hat);
                break;
            }

        }
    }
    @DeleteMapping
    public void deleteHat(@RequestParam int deleteId){
        for (Hat x :hatList
             ) {
            if(x.getId()==deleteId)hatList.remove(x);break;

        }
    }
}
