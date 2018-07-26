package exhibitionsystem.type;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.exhibition.domain.production_type;
import com.exhibition.productiontype.DTO.TypeCarouselDTO;
import com.exhibition.productiontype.service.ProductionTypeService;

/**
 * 类型测试类
 * @author LL
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class TypeTest {
	@Resource
	private ProductionTypeService productionTypeService;

	public ProductionTypeService getProductionTypeService() {
		return productionTypeService;
	}

	public void setProductionTypeService(ProductionTypeService productionTypeService) {
		this.productionTypeService = productionTypeService;
	}
/**
 * 删除测试
 */
	@Test
	public void deleteTest() {
		String idList = "Type001,Type002";
		productionTypeService.deleteProductionType(idList);
	}
	/**
	 *查询所有类型测试 
	 */
	@Test
	public void querryProductionTypeTest() {
		TypeCarouselDTO typeCarouselDTO = new TypeCarouselDTO();
		production_type productionType = new production_type();
		productionType.setProduction_type_id("Type001");
 		productionTypeService.querryProductionType(productionType);
	}
}
