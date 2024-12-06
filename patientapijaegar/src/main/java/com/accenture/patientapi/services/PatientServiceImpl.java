package com.accenture.patientapi.services;

import com.accenture.patientapi.exceptions.PatientIdNotFoundException;
import com.accenture.patientapi.exceptions.PatientMobileInValidException;
import com.accenture.patientapi.models.Patient;
import com.accenture.patientapi.repositories.PatientRepository;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private Tracer tracer;

    @Override
    public Patient addPatient(Patient patient) {
        if(patient.getContactNo()>0){
            return patientRepository.save(patient);
        }else {
            throw new PatientMobileInValidException("Mobile No should be in 10 digits");
        }
    }

    @Override
    public List<Patient> getAllPatients(Span rootSpan) {

        Span span = tracer.buildSpan("service fetching patient details")
                .asChildOf(rootSpan).start();

         List<Patient> patients=patientRepository.findAll();
         span.finish();
         return patients;
    }

    @Override
    public Patient getPatientById(long patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(()->new PatientIdNotFoundException("Patient Id Not Found Exception"));
    }

    @Override
    public List<Patient> getPatientByContactNo(long contactNo) {
        return this.patientRepository.findPatientByContactNo(contactNo);
    }

    @Override
    public Patient updateContactNo(long patientId, long newContactNo) {
        Patient patient=getPatientById(patientId);
        if(patient!=null){
            patient.setContactNo(newContactNo);
           return patientRepository.save(patient);
        }else {
            throw new PatientIdNotFoundException("Patient Id Not Found Exception");
        }
    }

    @Override
    public boolean deletePatientById(long patientId) {
        boolean status=false;
        Patient patient=getPatientById(patientId);
        if(patient!=null){
            patientRepository.deleteById(patientId);
            status=true;
        }

        return status;
    }
}
