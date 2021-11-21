package jpabook2.jpashop2.repository;

import jpabook2.jpashop2.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;
    public void save(Item item) {
        if (item.getId() == null) {   //처음에 아무 아이디 없으면(새로 생성)
            em.persist(item);
        } else {
            em.merge(item);    //업데이트랑 비슷
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }
    public List<Item> findAll() {
        return em.createQuery("select i from Item i",Item.class).getResultList();
    }
}

