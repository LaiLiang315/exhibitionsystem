package exhibitionsystem.carousel;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.exhibition.carousel.DTO.CarouselManagementDTO;
import com.exhibition.carousel.service.CarouselManagementService;


/**
 * 轮播图测试类
 * @author LL
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class CarouselTest {
	@Resource
	private CarouselManagementService carouselManagementService;

	public CarouselManagementService getCarouselManagementService() {
		return carouselManagementService;
	}

	public void setCarouselManagementService(CarouselManagementService carouselManagementService) {
		this.carouselManagementService = carouselManagementService;
	}
    @Test
	public void querryCarousel() {
    	List<CarouselManagementDTO> listCarouselDTO = carouselManagementService.querryCarousel();
    	System.out.println(listCarouselDTO);
		
	}
}
