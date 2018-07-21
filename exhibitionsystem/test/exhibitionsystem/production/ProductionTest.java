package exhibitionsystem.production;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.exhibition.domain.production_info;
import com.exhibition.production.VO.ProductionVO;
import com.exhibition.production.service.ProductionManagementService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 作品信息测试类
 * @author LL
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class ProductionTest {
	@Resource
	private ProductionManagementService productionManagementService;

	public ProductionManagementService getProductionManagementService() {
		return productionManagementService;
	}

	public void setProductionManagementService(ProductionManagementService productionManagementService) {
		this.productionManagementService = productionManagementService;
	}
	/**
	 * 作品分页测试
	 */
	@Test
	public void productionVO() {
		ProductionVO productionVO = new ProductionVO();
		String showAll = "0";
		/*String search = "夏";
		productionVO.setSearch(search);*/
	/*	String type = "Type002";
		productionVO.setType(type);*/
		productionVO = productionManagementService.showPicturesVO(showAll, productionVO);
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		System.out.println("wwwwwwwww"+gson.toJson(productionVO));
	}

}
