package org.springframework.samples.petclinic.recoveryroom;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recoveryroom")
public class RecoveryRoomController {
	public static final String ROOMS_FORM =  "recoveryroom/createOrUpdateRecoveryRoomForm";
	public static final String ROOMS_OK =  "welcome";

	
	@Autowired
	RecoveryRoomService rs;
	
	@GetMapping("/create")
	public String newRecoveryRoom(ModelMap model) {
		RecoveryRoom recoveryRoom = new RecoveryRoom();
		model.addAttribute("recoveryRoom", recoveryRoom);
		model.addAttribute("types", rs.getAllRecoveryRoomTypes());
		return ROOMS_FORM;
	}
	
	@PostMapping("/create")
	public String newRecoveryRoom(ModelMap model, @Valid RecoveryRoom recoveryRoom, BindingResult result) {
		if(result.hasErrors()) {
			model.addAttribute("message", result.getAllErrors());
			return ROOMS_FORM;
		}
		else {
			rs.save(recoveryRoom);
			return ROOMS_OK;
		}
	}
	
}
