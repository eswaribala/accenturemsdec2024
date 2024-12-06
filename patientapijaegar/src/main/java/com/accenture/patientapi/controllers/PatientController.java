package com.accenture.patientapi.controllers;

import com.accenture.patientapi.dtos.GenericResponse;
import com.accenture.patientapi.dtos.PatientRequest;
import com.accenture.patientapi.models.FullName;
import com.accenture.patientapi.models.Patient;
import com.accenture.patientapi.services.PatientService;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.tag.Tags;
import io.opentracing.util.GlobalTracer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("patients")
public class PatientController {

    @Autowired
    private PatientService patientService;
    @Autowired
    private Tracer tracer;

    @PostMapping("/v1.0")
    ResponseEntity<GenericResponse> addPatient(@Valid @RequestBody PatientRequest patientRequest){

        Patient patient=Patient.builder()
                .dob(patientRequest.getDob())
                .patientType(patientRequest.getPatientType())
                .contactNo(patientRequest.getContactNo())
                .fullName(FullName.builder()
                        .firstName(patientRequest.getFullNameRequest().getFirstName())
                        .lastName(patientRequest.getFullNameRequest().getLastName())
                        .build())
                .gender(patientRequest.getGender())
                .build();

       Patient patientObj= patientService.addPatient(patient);


         return  ResponseEntity.status(HttpStatus.CREATED)
                   .body(new GenericResponse(patientObj));



    }

    @GetMapping("/v1.0")
    public List<Patient> getAllPatients(){
        HttpStatus status=null;
        Tracer tracer = GlobalTracer.get();
        Tracer.SpanBuilder spanBuilder = tracer.buildSpan("PatientSpan")
                .withTag(Tags.SPAN_KIND.getKey(), Tags.SPAN_KIND_SERVER);

        Span span1 = spanBuilder.start();
        Tags.COMPONENT.set(span1, "PatientController");
        span1.setTag("testtag", "test");
        span1.finish();
        Span span = tracer.buildSpan("accessing patients").start();
        List<Patient> patients= this.patientService.getAllPatients(span);
        if ( patients.size()>0) {
            status = HttpStatus.CREATED;
            span.setTag("http.status_code", 201);
        } else {
            span.setTag("http.status_code", 403);
        }
        span.finish();
        return patients;
    }

    @GetMapping("/v1.0/{patientId}")
    public Patient getPatientById(@PathVariable("patientId") long patientId){
        return this.patientService.getPatientById(patientId);
    }

    @GetMapping("/v1.0/filter")
    public List<Patient> getPatientByContactNo(@RequestParam("contactNo") long contactNo){
        return this.patientService.getPatientByContactNo(contactNo);
    }

    @PutMapping("/v1.0")
    public Patient updatePatientByPatientId(@RequestParam("patientId") long patientId,@RequestParam("contactNo") long contactNo){
        return this.patientService.updateContactNo(patientId,contactNo);
    }

    @DeleteMapping("/v1.0")
    public ResponseEntity<GenericResponse> deletePatientByPatientId(@RequestParam("patientId") long patientId){
        if(patientService.deletePatientById(patientId))
            return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse("Deleted"));
        else
            return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse("Not Deleted"));
    }
}
