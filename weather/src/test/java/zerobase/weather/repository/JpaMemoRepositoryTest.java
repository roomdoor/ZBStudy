package zerobase.weather.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.domain.Memo;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JpaMemoRepositoryTest {

    @Autowired
    JpaMemoRepository jpaMemoRepository;

    @DisplayName("01. save, find all")
    @Test
    public void test_01() {
        //given
        Memo memo = new Memo(2, "memo memo");

        //when
        jpaMemoRepository.save(memo);

        //then
        List<Memo> list = jpaMemoRepository.findAll();

        for (Memo m : list) {
            System.out.println(m.toString());
        }
        assertNotNull(list);
    }

    @DisplayName("02. find all")
    @Test
    public void test_02() {
        //given
        Memo memo = new Memo(13, "mmmmm");
        //when
        Memo save = jpaMemoRepository.save(memo);
        //then
        Optional<Memo> byId = jpaMemoRepository.findById(save.getId());
        assertEquals(byId.get().getText(), "mmmmm");
    }
}