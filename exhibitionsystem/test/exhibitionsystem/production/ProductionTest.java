package exhibitionsystem.production;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.exhibition.domain.production_info;
import com.exhibition.production.DTO.ProductionInfoDTO;
import com.exhibition.production.VO.ProductionVO;
import com.exhibition.production.service.ProductionManagementService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import util.BuildMd5;

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
		String showAll = "1";
		/*String search = "夏";
		productionVO.setSearch(search);*/
	/*	String type = "Type002";
		productionVO.setType(type);*/
		productionVO.setPageIndex(1);
		productionVO = productionManagementService.showPicturesVO(showAll, productionVO);
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		System.out.println("wwwwwwwww"+gson.toJson(productionVO));
	}
	@Test
	public void getProductionInfo() {
		List<ProductionInfoDTO> listProductionInfoDTO = productionManagementService.getProductionInfo();
		System.out.println("DDDDDD"+listProductionInfoDTO);
		
	}
	@Test
	public void Md5Test() {
		String pwd ="111111";
		BuildMd5 MD5 =new BuildMd5();
		System.out.println(MD5.getMD5Code(pwd));;
	}
    @Test
    public void querryAllProductionTest() {
    	ProductionVO productionVO = new ProductionVO();
    	productionVO = productionManagementService.querryAllProduction(productionVO);
    }
}
