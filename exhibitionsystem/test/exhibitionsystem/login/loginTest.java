package exhibitionsystem.login;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.exhibition.domain.adminAcount;
import com.exhibition.login.service.AdminLoginService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class loginTest {
	@Resource
	private AdminLoginService adminLoginService;

	public AdminLoginService getAdminLoginService() {
		return adminLoginService;
	}

	public void setAdminLoginService(AdminLoginService adminLoginService) {
		this.adminLoginService = adminLoginService;
	}

	@Test
	public void login() {
		adminAcount admin = new adminAcount();
		admin.setUsername("root");
		admin.setPassword("root");
		adminLoginService.adminLogin(admin);

	}
}
