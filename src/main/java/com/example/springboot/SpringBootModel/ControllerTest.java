package com.example.springboot.SpringBootModel;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@SessionAttributes("sessionuser")
@org.springframework.stereotype.Controller
public class ControllerTest {
	
	/*İstek atıldığı zaman, Spring @SessionAttribute("sessionusre") annt'dan dolayi, session attr'de "sessionuser" var mı diye bakar.
	  Varsa model'a o attr'u ekler.
	  Yoksa,@ModelAttribute('sessionuser') ile işaretlenmiş metodu(return new User()) çalistirir.Session ve Model'a bu attr eklenir.
	  		Eklenen attr session sonuna kadar sessionda orda kalir.*/
	
	/*Bu örnekte, önce controller sinifindaki testsession metoduna istek atiyorum. Orada session'a "sessionuser" attr'u ekleniyor.
	 * O yüzden bu sinifa istek geldiğinde,Spring session'da "sessionuser" attr'u var mı diye bakıyor.
	 * Olduğu için, model'a bu attr'u atıyor. Yani burada populate metodu calismiyor. Yeni bir session attr atanmiyor. 
	 * Diger sinifta atadgım attr kullaniliyor*/
	@GetMapping("/testsessional")
	public String testsessional(Model model,HttpSession ses,SessionStatus sessionStatus){
		System.out.println("TEST SESSİON AL");
		System.out.println("Model'dan SessionUser:"+ ((User) model.asMap().get("sessionuser")).getIsim() +" "+((User) model.asMap().get("sessionuser")).getSoyisim());
		System.out.println("HTTP SESSİON ATTRİBUTE'den SessionUser:"+ses.getAttribute("sessionuser")); //Userı getirdi.
		System.out.println("****");
		return "result";
	}
	//sessionStatus.setComplete(); Attr'u session'dan siler. (Model'dan değil).
	
	/* Genel Kullanım*/
	@GetMapping("/testsessional2")
	public String testsessional2(@ModelAttribute("sessionuser") User sessionuser ,Model model,HttpSession ses){
		System.out.println("TEST SESSİON AL 2");
		System.out.println("Model'dan SessionUser 2:"+ ((User) model.asMap().get("sessionuser")).getIsim() +" "+((User) model.asMap().get("sessionuser")).getSoyisim());
		System.out.println("HTTP SESSİON ATTRİBUTE'den SessionUser 2:"+ses.getAttribute("sessionuser")); //Userı getirdi.
		System.out.println(sessionuser.getIsim()+" "+sessionuser.getSoyisim());
		System.out.println("****");
		return "result";
	}
	
	@ModelAttribute("sessionuser")
	public User populate() {
		System.out.println("SESSİON ATTR DOLDUR 2");
		User user = new User();
		user.setIsim("Hasan2");
		user.setSoyisim("Falan2");
		return user;
	}
	
	
	
	
	
	
	
	/* Sınıf üstünde kullanılan @SessionAttributes("x") annt'unda; x session'da var mı diye bakılır, varsa MODEL'a aktarılır.
	 * Yoksa @ModelAttr('x')'li fonk çağıralarak MODEL'a ve sessiona aktarilir. Biz de bu attr'u session veya model nesnesi üzerinden kullaniriz.
	 * 
	 * Parametreli kullanımda ise, Model'a aktarım gerçekleşmez.Spring, User sessionuser nesnesini, direkt HttpSession üzerinden bağlar.
	 * Model nesnesine dokunmaz. Sessionda yoksa herhangi bir metod çağırmaz(req true ise hata fırlatir).
	 * Bir bakıma sadece şu işlemi yapar;
	 * User sessionuser = ses.getAttribute("sessionuser"));
	 */
	
	@GetMapping("/testsessionalparam")
	public String testsessionalparam(@SessionAttribute(name = "sessionuser",required = false) User sessionuser,Model model,HttpSession ses){
		System.out.println("TEST SESSİON AL 2");
		System.out.println("Model:"+model.toString());
		//System.out.println("Model'dan SessionUser:"+ ((User) model.asMap().get("sessionuser")).getIsim() +" "+((User) model.asMap().get("sessionuser")).getSoyisim());
		System.out.println("HTTP SESSİON ATTRİBUTE'den SessionUser:"+ses.getAttribute("sessionuser")); //Userı getirdi.
		System.out.println("PARAMETREDEN SessionUser"+sessionuser.getIsim() +" "+sessionuser.getSoyisim());
		System.out.println("****");
		return "result";
	}
}
