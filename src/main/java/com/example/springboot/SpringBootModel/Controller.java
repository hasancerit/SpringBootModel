package com.example.springboot.SpringBootModel;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes("sessionuser")
@org.springframework.stereotype.Controller
public class Controller {					/* Method modelattribute */
/*	//Her req'den önce calisirlar. Model sınıfına value isiminde, return degerinde bir attr eklerler
	@ModelAttribute(value = "msg1")
	public String ekle1() {
		System.out.println("String ekle calisti");
		return "Deger1";
	}
	
	//Her req'den önce calisirlar. Model sınıfını parametre ile çekerek attr ekledik.
	@ModelAttribute
	public void ekle2(Model model){
		System.out.println("String ekle2 calisti");
		model.addAttribute("msg2","Deger 2");
		model.addAttribute("msg3","Deger 3");
	}
	
	//Karisik. Hem parametre ile attr ekledik hem de value,return ile.
	@ModelAttribute(value = "msg6")
	public String ekle3(Model model){
		System.out.println("String ekle3 calisti");
		model.addAttribute("msg4","Deger 4");
		model.addAttribute("msg5","deger5");
		return "Deger 6def";
	}
	
	//ModelAttr metodu reqparam da kullabilir. /test'e atilan requestte bulunan parametreler, bu methodlara da gçnderilir
	@ModelAttribute(value = "msg7")
	public String ekle4(@RequestParam(required = false) String msg7,Model model){
		//System.out.println("String ekle4 calisti");
		return msg7;
	}
	
	@GetMapping("/test")
	public String test(Model model,HttpSession session,HttpServletRequest req){
		//Buraya istek geldginde ilk önce @modelattr'li methodlar calisir. Bu methodlar model sinifini doldurur
		//Bu model sinifi, result'a gönderilir
		model.addAttribute("msg1","Yeni Deger Message1");
		System.out.println(model.toString());
		System.out.println("Session: "+session.getId());
		System.out.println("Controller: "+this.toString());
		System.out.println("Session Attrs"+session.getAttributeNames().toString());
		System.out.println("Req Attrs:"+req.getAttribute("msg1"));
		return "result";
	}*/
	

	
									/* Parametre Model attr */
	 /*Parametre olarak modelattr kullanilirsa, öncelikle modelda "msg1" adinda bir attr var mi diye bakilir.
	 * Varsa, st degiskenine direkt o attr atanır.(Req ile msg1 parametresi yollansa da attr'e set edilmez ve st'ye atanmaz)
	 * Yoksa,boş bir string oluşturulur, modelattr ve st'ye atanır.(req ile msg1 paramı gönderildiyse modelattr'e set edilir ve st'ye atanir)*/	 
	@GetMapping("/testparam")
	public String testparam(@ModelAttribute("msg1") String st,Model model){
		System.out.println(model.toString());
		System.out.println("Message1: "+st);
		return "result";
	}
	
	
	/* Parametre olarak modelattr kullanilirsa (pojo class ile), model'da "user" adında bir attr var mı diye bakilir.
	 * Varsa, o kullanilir. (request ile user nesnesi alanları yollanırsa attr'e set edilir, us'a atanır)
	 * Yoksa, boş bir user oluşturulup us ve modela atanır.(Req paramları ile user alanları gönderilmiş ise onlar da bu nesneye set edilir ve us'ye atanir)*/
	@GetMapping("/testparamuser")
	public String testparamuser(@ModelAttribute("user") User us,Model model){
		System.out.println(model.toString());
		System.out.println(us.getIsim()+" "+ us.getSoyisim());
		return "result";
	}
	
	/*@ModelAttribute(value = "user")
	public User ekleuser() {
		//System.out.println("User ekle calisti");
		User user = new User();
		user.setIsim("HasanMethod");
		user.setSoyisim("CeritMethod");
		return user;
	}*/
	
	
													/*Örnek*/
	/*@GetMapping("/form")
	public String sendForm(Model model) {
		System.out.println(model.toString());
		return "form";
	}
	
	@PostMapping("/form")
	public String handleForm(@ModelAttribute("user") User us,Model model) {
		System.out.println(model.toString());
		System.out.println(us.getIsim()+" "+ us.getSoyisim());
		return "result";
	}

	*/										
			
												/*Session Attr*/
	
	/*İstek atıldığı zaman, Spring @SessionAttribute("sessionusre") annt'dan dolayi, session attr'de "sessionuser" var mı diye bakar.
	  Varsa model'a o attr'u ekler.
	  Yoksa,@ModelAttribute('sessionuser') ile işaretlenmiş metodu(return new User()) çalistirir.Session ve Model'a bu attr eklenir.*/
	
	/*Bu önekte, önce bu sinifdaki testsession metoduna istek atiyorum. Spring session'da "sessionuser" attr'u var mı diye bakiyor.
	 *Olmadığı için populate metodu çalisip, session ve model'a bu attr'u ekliyor.
	*/
	
	@GetMapping("/testsession")
	public String testsession(Model model,HttpSession ses){
		return "result";
	}

	@ModelAttribute("sessionuser")
	public User populate() {
		System.out.println("SESSİON ATTR DOLDUR");
		User user = new User();
		user.setIsim("Hasan");
		user.setSoyisim("Falan");
		return user;
	}
}
