package com.example.jobportal;

public class JobList {
    private String title;

    private String payment;
    private String location;
    private String workHours;
    private String description;
    private String vacancy;
    private String contact;
    private String email;
   // private String avatar_url;
    private String html_url;
    public JobList(String title,
                   String payment, String location,
                   String workHours, String description,
                   String vacancy,
                   String contact, String email
                 ){
        this.title=title;

        this.payment=payment;
        this.location=location;
        this.workHours=workHours;
        this.description=description;
        this.vacancy=vacancy;
        this.contact=contact;
        this.email=email;

    }
    public String getTitle(){ return title; }

    public String getPayment(){ return payment; }
    public String getLocation(){ return location; }
    public String getWorkHours(){ return workHours; }
    public String getDescription(){ return description; }
    public String getVacancy(){ return vacancy; }
    public String getContact(){ return contact; }
    public String getEmail(){ return email; }


//    public String getAvatar_url(){
//        return avatar_url;
//    }

}
