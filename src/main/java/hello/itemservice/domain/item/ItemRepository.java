package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ItemRepository {

    //HashMap<> 실무에서 사용하지말고 멀티쓰레드 환경에서 HashMap<> 사용하면 안됨 싱글쓰레드 됨
    //ConcurrentHashMap 이거 사용해야함
    // private  static long sequence =0 이것도 long 노우노우
    private static final Map<Long, Item> store = new HashMap<>(); //static
    private  static long sequence =0L; //static

    public Item save (Item item){
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id){
        return store.get(id);
    }

    public List<Item> findAll(){
        //ArrayList로 한번 감싼 이유는 ? 실제 store에는 변함없으니 안전하게
        return new ArrayList<>(store.values());
    }

    //요런건 별도의 업데이트용 요 3개 파라미터의 객체를 만드는게 나음 ex) ItemParamDto
    // 왜 id는 사용 안하지? 혼란스러울 수 있음
    public void update(Long itemId, Item updateParam){
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());

    }

    //테스트용에서 사용하려고 만듬
    public void  clearStore(){
        store.clear();
    }
}
