package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicController {

    private final ItemRepository itemRepository;
    //@RequiredArgsConstructor 롬복 이거 사용하면 아래 만들어줌
    //생성자 하나면  @Autowired 생략가능능
//    @Autowired
//    public BasicController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }


    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

    //@PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                        Model model){

        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);
        model.addAttribute("item", item);


        return "basic/item";
    }

   // @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item){
    //@ModelAttribute("item") 여기에 지정해준 이름으로 뷰에 넣어줌 , 객체도 만들어줌

        itemRepository.save(item);
       // model.addAttribute("item", item);// 자동 추가 , 생략 가능
        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item){
       //Item -> item이 모델에 담기게 됨
        //@ModelAttribute("item") 여기에 지정해준 이름으로 뷰에 넣어줌 , 객체도 만들어줌

        itemRepository.save(item);
        // model.addAttribute("item", item);// 자동 추가 , 생략 가능
        return "basic/item";
    }

  //  @PostMapping("/add")
    public String addItemV4( Item item){

        itemRepository.save(item);
        // model.addAttribute("item", item);// 자동 추가 , 생략 가능
        return "basic/item";
    }

      @PostMapping("/add")
    public String addItemV5( Item item){

        itemRepository.save(item);
        return "redirect:/basic/items/" + item.getId();
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item){
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

    /**
     *테스트용 데이터 추가
     */
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA",10000,10));
        itemRepository.save(new Item("itemB",20000,20));

     }

}
