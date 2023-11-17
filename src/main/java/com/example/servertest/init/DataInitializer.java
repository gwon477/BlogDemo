package com.example.servertest.init;

import com.example.servertest.dto.Item;
import com.example.servertest.repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer implements ApplicationRunner {
    @Autowired
    private ItemRepository itemRepository;


    public void run(ApplicationArguments args ) throws Exception{
        if(itemRepository.count() == 0){
            Item itemA = new Item();
            itemA.setItem_Name("A ITEM");
            itemA.setItem_Info("A ITEM GOOD");
            itemA.setPrice(10);
            itemA.setCount(0);

            Item itemB = new Item();
            itemB.setItem_Name("B ITEM");
            itemB.setItem_Info("B ITEM NICE");
            itemB.setPrice(50);
            itemB.setCount(0);

            Item itemC = new Item();
            itemC.setItem_Name("C ITEM");
            itemC.setItem_Info("C ITEM PERFECT");
            itemC.setPrice(150);
            itemC.setCount(0);

            itemRepository.saveAll(Arrays.asList(itemA,itemB,itemC));
        }
    }

}
