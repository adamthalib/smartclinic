package com.mitrais.smartclinic.controller;

import com.mitrais.smartclinic.model.Patient;
import com.mitrais.smartclinic.repository.PatientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import javax.validation.Valid;

@Controller
@RequestMapping("/patients")
public class PatientController {

    private final
    PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping
    public String patient(Model model){
        List<Patient> patients = patientRepository.findAll();
        model.addAttribute("patients", patients);
        return "patients/patient";
    }

    @GetMapping("/add")
    public String showPatientForm(Model model) {
        Patient patient = new Patient();
        model.addAttribute(patient);
        return "patients/form-add";
    }

    @PostMapping("/add")
    public String savePatient(@Valid Patient patient) {
        patientRepository.save(patient);
        return "redirect:/patients";
    }

    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable("id") Long id, Model model){
        Patient patient = patientRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("patient", patient);
        return "patients/form-edit";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Patient patient){
        patientRepository.save(patient);
        return "redirect:/patients";
    }

    @GetMapping(value = "/delete/{id}")
    public String deletePatient(@PathVariable("id") Long id){
        patientRepository.deleteById(id);
        return "redirect:/patients";
    }
}
