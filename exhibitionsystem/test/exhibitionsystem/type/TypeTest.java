package exhibitionsystem.type;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
	
}
