package com.example.springboot.SpringBootModel;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes("sessionuser")
@org.springframework.stereotype.Controller
public class ControllerTest {
	/*İstek atıldığı zaman, Spring @SessionAttribute("sessionusre") annt'dan dolayi, session attr'de "sessionuser" var mı diye bakar.
	  Varsa model'a o attr'u ekler.
	  Yoksa,@ModelAttribute('sessionuser') ile işaretlenmiş metodu(return new User()) çalistirir.Session ve Model'a bu attr eklenir.*/
	
	/*Bu örnekte, önce controller sinifindaki testsession metoduna istek atiyorum. Orada session'a "sessionuser" attr'u ekleniyor.
	 * O yüzden bu sinifa istek geldiğinde,Spring session'da "sessionuser" attr'u var mı diye bakıyor.
	 * Olduğu için, model'a bu attr'u atıyor. Yani burada populate metodu calismiyor. Yeni bir session attr atanmiyor. 
	 * Diger sinifta atadgım attr kullaniliyor*/
	@GetMapping("/testsessional")
	public String testsessional(Model model,HttpSession ses){
		System.out.println("TEST SESSİON MODEL:"+model.toString());
		System.out.println("SessionUser:"+ ((User) model.asMap().get("sessionuser")).getIsim() +" "+((User) model.asMap().get("sessionuser")).getSoyisim());
		return "result";
	}
	
	@ModelAttribute("sessionuser")
	public User populate() {
		System.out.println("SESSİON ATTR DOLDUR");
		User user = new User();
		user.setIsim("Hasan2");
		user.setSoyisim("Falan2");
		return user;
	}
}
