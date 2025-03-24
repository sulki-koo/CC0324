package cookcloud.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cookcloud.entity.MemberAllergyFood;
import cookcloud.service.AllergyService;
import cookcloud.service.MemberAllergyFoodService;
import cookcloud.service.MemberService;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;
    
    @Autowired
    private AllergyService allergyService;
    
    @Autowired
    private MemberAllergyFoodService memberAllergyFoodService;

    // 회원가입 페이지
    @GetMapping("/signup")
    public String showSignupPage(Model model) {
    	model.addAttribute("allergyList", allergyService.getAllAllergies());
        return "signup";  // signup.html 페이지 반환
    }

    // 회원가입 처리
    @PostMapping("/signup")
    public String registerMember(@RequestParam String memId, 
                                 @RequestParam String memPassword,
                                 @RequestParam String memName,
                                 @RequestParam String memNickname,
                                 @RequestParam String memEmail,
                                 @RequestParam String memPhone,
                                 @RequestParam List<Long> selectedAllergies,
                                 Model model) {
        try {
            memberService.registerMember(memId, memPassword, memName, memNickname, memEmail, memPhone);
            // 알러지 정보 저장
            if (selectedAllergies != null && !selectedAllergies.isEmpty()) {
                for (Long allergyId : selectedAllergies) {
                    MemberAllergyFood memberAllergyFood = new MemberAllergyFood();
                    memberAllergyFood.setMemId(memId);
                    memberAllergyFood.setAllergyId(allergyId);
                    memberAllergyFood.setMemAllergyInsertAt(LocalDateTime.now());
                    memberAllergyFood.setMemAllergyIsDeleted("N");  // 초기값 설정
                    memberAllergyFoodService.insertMemAllergyFood(memberAllergyFood);
                }
            }
            
            model.addAttribute("message", "회원가입 성공! 로그인하세요.");
            return "login";  // 회원가입 후 로그인 페이지로 이동
        } catch (Exception e) {
            model.addAttribute("error", "회원가입 실패: " + e.getMessage());
            return "signup";  // 실패 시 다시 회원가입 페이지로 이동
        }
    }
}
