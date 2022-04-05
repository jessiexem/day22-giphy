package vttp.paf.day22;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vttp.paf.day22.model.Giphy;
import vttp.paf.day22.service.GiphyService;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class Day22ApplicationTests {

	@Autowired
	GiphyService giphyService;

	@Test
	void contextLoads() {
	}

	@Test
	void shouldHave10Giphy() {
		Optional<List<Giphy>> opt = giphyService.getGiphys("pokemon");
		List<Giphy> list = opt.get();
		Assertions.assertEquals(10,list.size());
	}

}
