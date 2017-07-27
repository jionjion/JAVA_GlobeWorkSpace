package MicroServices.collector;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import MicroServices.bean.User;
import MicroServices.dao.UserRepository;

/**	用户的控制层类*/
@RestController()
@RequestMapping("/user")
@Transactional			//事务接口,表示该类同时为事务
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	
	/**	查询全部
	 * 	URL: http://localhost:8080/MicroServices/user/users
	 * */
	@GetMapping(value="/users")			//get方式,获得全部信息
	public List<User> userList() {
	
		return userRepository.findAll();
	}
	
	/**	新增一个
	 * 	URL: http://localhost:8080/MicroServices/user/users
	 */
	@PostMapping(value="/users")		//post方式,添加信息
	@Transactional						//事务接口,表示该方法为事务
	public User	 userSave(	@RequestParam("username") String username,
							@RequestParam("password") String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		return userRepository.save(user);
	}
	
	/**	查询一个
	 * 	URL: http://localhost:8080/MicroServices/user/users  
	 * */
	@GetMapping(value="/users/{id}")			//get方式,获得全部信息
	public User userGet(@PathVariable("id") Integer id) {
		
		return userRepository.findOne(id);
	}
	
	/**	更新一个
	 * 	URL: http://localhost:8080/MicroServices/user/users/3 
	 */
	@PutMapping(value="/users/{id}")			//get方式,获得全部信息
	public User userUpdate(	@PathVariable("id") Integer id,
							@RequestParam("username") String username,
							@RequestParam("password") String password) {
		
		User user = new User();
		user.setId(id); 						//保存主键
		user.setUsername(username);
		user.setPassword(password);
		return userRepository.save(user);		//当保存主键一致时,为更新操作
	}
	
	/**	删除一个
	 * 	URL: http://localhost:8080/MicroServices/user/users/3  
	 * */
	@DeleteMapping(value="/users/{id}")			//get方式,获得全部信息
	public void userDelete(@PathVariable("id") Integer id) {
		userRepository.delete(id);
	}
	
	
	/**	自定义查询,通过姓名查询用户
	 * 	URL: http://localhost:8080/MicroServices/user/users/username/Jion  
	 * */
	@GetMapping(value="/users/username/{username}")			//get方式,获得全部信息,注意路径不要冲突
	public List<User> userGetByUsername(@PathVariable("username") String username) {
		
		return  userRepository.findByUsername(username);
	}
}
