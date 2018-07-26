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
	/**
	 * 查询轮播图测试
	 */
    @Test
	public void querryCarousel() {
    	List<CarouselManagementDTO> listCarouselDTO = carouselManagementService.querryCarousel();
    	System.out.println("kkkkkkk"+listCarouselDTO);
		
	}
    /**
     * 批量删除测试
     */
    @Test
    public void deleteCarousel() {
    	String idList = "C001,C002,C003";
    	carouselManagementService.deleteCarousel(idList);
    }
}
