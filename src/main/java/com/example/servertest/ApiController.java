package com.example.servertest;

// UserController.java
import com.example.servertest.dto.Inven;
import com.example.servertest.dto.User;
import com.example.servertest.dto.Item;
import com.example.servertest.repo.InvenRepository;
import com.example.servertest.repo.ItemRepository;
import com.example.servertest.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
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

    //Store Page Service
    @GetMapping("/Store/api/userpoint/{userEmail}")
    public ResponseEntity<Integer> getUserPoint(@PathVariable String userEmail){

        Optional<User> userOptional = userRepository.findByUserEmail(userEmail);

        // Check if the user exists
        if (userOptional.isPresent()) {
            System.out.println("userOptional.get().getPoint() = " + userOptional.get().getPoint());
            // User exists, return the point
            return ResponseEntity.ok(userOptional.get().getPoint());
        } else {
            System.out.println("ResponseEntity.notFound().build() = " + ResponseEntity.notFound().build());
            // User not found, return 404
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/Store/api/purchase")
    public String updatePoint(@RequestParam String userEmail, @RequestParam int point, @RequestParam Long itemId){
        System.out.println("userEmail = " + userEmail);
        System.out.println("point = " + point);
        System.out.println("itemId = " + itemId);

        // 인벤토리에 구입한 아이템 추
        Inven inven = new Inven();

        inven.setItemId(itemId);
        inven.setUserEmail(userEmail);

        invenRepository.save(inven);
        System.out.println(" 인벤토리에 구입한 아이템 추가 ");

        //구입한 아이템을 아이템 목록에서 카운트 추가

        Item item = itemRepository.findById(itemId).orElse(null);
        System.out.println("item = " + item);

        int cnt = item.getCount() + 1;
        item.setCount(cnt);
        itemRepository.save(item);
        System.out.println(" 입한 아이템을 아이템 목록에서 카운트 추가 " );

//        //user를 찾아서 포인트 업데이트
        User user = userRepository.findByUserEmail(userEmail).orElse(null);
        if(user != null){
            user.setPoint(point);
            userRepository.save(user);
            return "Point update successfully";
        }else {
            return "ID Not Found";
        }
    }

    @GetMapping("/Store/api/invencheck/{userEmail}")
    public ResponseEntity<List<Long>> getItemIdByUserId(@PathVariable String userEmail) {
        List<Inven> userInventory = invenRepository.findByUserEmail(userEmail);
        System.out.println("userInventory = " + userInventory);

        if (!userInventory.isEmpty()) {
            // Extract itemIds from the inventory
            List<Long> itemIds = userInventory.stream()
                    .map(Inven::getItemId)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(itemIds);
        } else {
            // User not found or has an empty inventory, return 404
            return ResponseEntity.notFound().build();
        }
    }


    //Inventory Page
    @GetMapping("/Inven/api/getItem/{userEmail}")
    public List<Map<String,String>> getItemByUserId(@PathVariable String userEmail) {
        List<Inven> items = invenRepository.findByUserEmail(userEmail);
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
//
//

    //Login Page Service
    @GetMapping("Login/api/signup")
    public String[] signUp(@RequestParam String userEmail, @RequestParam String blogName, @RequestParam String nickName) {
        // 여기서 userDto의 userEmail을 사용하여 DB 조회 및 처리를 수행
        // 이는 실제 데이터베이스 연동이므로, 여기에선 가정하고 코드 작성

        System.out.println("email = " + userEmail);
        System.out.println("blogName = " + blogName);
        System.out.println("nickName = " + nickName);

        // 가정: DB에서 userEmail이 존재하는지 확인
        Optional<User> existingUser = userRepository.findByUserEmail(userEmail);


        if (existingUser.isPresent()) {
            System.out.println("existingUser.isPresent() = " + existingUser.isPresent());
            // 이미 존재하는 사용자
            // [1, NickName] 반환
            return new String[]{"1", existingUser.get().getUserEmail()};
        } else {
            System.out.println("existingUser.isPresent() = " + existingUser.isPresent());
            // 새로운 사용자 생성
            User newUser = new User();
            newUser.setUserEmail(userEmail);
            newUser.setBlogName(blogName);
            newUser.setNickName(nickName);

            userRepository.save(newUser);

            // 이 부분에서는 실제 DB에 데이터를 저장하는 로직을 작성해야 함
            // userRepository.save(newUser); // 예시: Spring Data JPA를 사용하는 경우

            // [0, NickName] 반환
            return new String[]{"0", newUser.getUserEmail()};
        }
    }
}

