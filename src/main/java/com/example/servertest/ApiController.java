package com.example.servertest;

// UserController.java
import com.example.servertest.dto.Inven;
import com.example.servertest.dto.User;
import com.example.servertest.dto.Item;
import com.example.servertest.repo.InvenRepository;
import com.example.servertest.repo.ItemRepository;
import com.example.servertest.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ApiController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InvenRepository invenRepository;

    @Autowired
    private ItemRepository itemRepository;


    @GetMapping("/api/userData/{userId}")
    public ResponseEntity<Integer> getUserPoint(@PathVariable Long userId){

        //System.out.println("userId = " + userId);

        int userPoint = userRepository.findById(userId)
                .map(User::getPoint)
                .orElse(-1);

        //System.out.println("userPoint = " + userPoint);
        
        return userPoint != -1 ? ResponseEntity.ok(userPoint) : ResponseEntity.notFound().build();
    }

    @GetMapping("/api/purchase/{userId}")
    public String updatePoint(@PathVariable Long userId, @RequestParam int point, @RequestParam Long itemId){
        System.out.println("userId = " + userId);
        System.out.println("point = " + point);
        System.out.println("itemId = " + itemId);

        // 인벤토리에 구입한 아이템 추
        Inven inven = new Inven();

        inven.setItemId(itemId);
        inven.setUserId(userId);

        invenRepository.save(inven);
        System.out.println(" 인벤토리에 구입한 아이템 추가 ");

        //구입한 아이템을 아이템 목록에서 카운트 추가
        Item item = itemRepository.findById(itemId).orElse(null);
        int cnt = item.getCount() + 1;
        item.setCount(cnt);
        itemRepository.save(item);
        System.out.println(" 입한 아이템을 아이템 목록에서 카운트 추가 " );

        //user를 찾아서 포인트 업데이트
        User user = userRepository.findById(userId).orElse(null);
        if(user != null){
            user.setPoint(point);
            userRepository.save(user);
            return "Point update successfully";
        }else{
            return "ID Not Found";
        }
    }

    @GetMapping("/Inven/api/{userId}")
    public List<Map<String,String>> getItemByUserId(@PathVariable Long userId) {
        List<Inven> items = invenRepository.findByUserId(userId);
        System.out.println("items = " + items);
        List<Long> itemIds = items.stream().map(Inven::getItemId).collect(Collectors.toList());
        System.out.println("itemIds = " + itemIds);

        List<Item> itemList = itemRepository.findByIdIn(itemIds);
        System.out.println("itemList = " + itemList);

        List<Map<String, String>> itemResponses = new ArrayList<>();

        for(Item item : itemList){
            Map<String, String> response = new HashMap<>();
            response.put("itemName", item.getItem_Name());
            response.put("itemInfo", item.getItem_Info());
            itemResponses.add(response);
        }
        return itemResponses;
    }

    @GetMapping("api/invencheck/{userId}")
    public List<Long> getItemIdByUserId(@PathVariable Long userId) {
        List<Inven> items = invenRepository.findByUserId(userId);
        System.out.println("items = " + items);
        List<Long> itemIds = items.stream().map(Inven::getItemId).collect(Collectors.toList());
        System.out.println("itemIds = " + itemIds);

        return itemIds;
    }
}

