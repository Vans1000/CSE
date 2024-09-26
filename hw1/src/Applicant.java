/**
 * @author Vanshdeep Singh
 * Stonybrook ID: 116535948
 * Recitation: 01
 */

public class Applicant implements Cloneable {
    private String[] companyName;
    private String applicantName;
    private double gpa;
    private String college;
    private String[] skills;
    private boolean swappedComp = false;
    private boolean swappedSkill = false;
    /**
     * Constructs a new Applicant
     * @param companyName
     * Array of strings which holds all companies of the Applicant
     * @param applicantName
     * String to hold applicants name
     * @param gpa
     * Double that holds applicants GPA
     * @param college
     * String that holds applicants gpa
     * @param Skills
     * Array of strings that holds applicants strings
     */
    public Applicant(String[] companyName,String applicantName,double gpa,String college,String[] Skills) {
        setCompanyName(companyName);
        setApplicantName(applicantName);
        setGpa(gpa);
        setCollege(college);
        setSkills(Skills);
    }
    /**
     * Getter of String of companyName
    * @return
     * String version of Array of companyName
     */
    public String getCompanyNameString() {
        int emptyCount = 0;
        for(int i = 0; i < companyName.length; i++){
            if(companyName[i]==null){
                emptyCount++;
            }else if(companyName[i].equals("")){
                emptyCount++;
            }
        }
        if(emptyCount == companyName.length){
            return "";
        }
        if(swappedComp == false) {
            for (int i = 0; i < companyName.length / 2; i++) {
                String temp = companyName[companyName.length - i - 1];
                companyName[companyName.length - i - 1] = companyName[i];
                companyName[i] = temp;
            }
            swappedComp = true;
        }
        String compString = "";
        for(int i = 0; i < companyName.length; i++){
            compString += companyName[i] + ", ";
        }
        compString = compString.replace("[", "").replace("]", "").replace("null", "").replace(" , ", "").replace(",,","").replace(", ",",");
        if(compString.charAt(0) == ','){
            compString= compString.substring(1);
        }
        if(compString.charAt(compString.length()-1) == ','){
            compString= compString.substring(0, compString.length()-1);
        }
        return compString;
    }

    /**
     *Getter for Array of Company Name
     * @return
     * String array of company names that the applicant worked at
     */
    public String[] getCompanyName() {
        return companyName;
    }

    /**
     * Setter for array of company name
     * @param companyName
     * Array that contains all company names
     */
    public void setCompanyName(String[] companyName) {
        this.companyName = companyName;
    }

    /**
     *Getter for applicant name
     * @return
     * Applicant's name as string
     */
    public String getApplicantName() {
        return applicantName;
    }

    /**
     * Setter for applicant name
     * @param applicantName
     * String that has applicant name
     */
    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    /**
     *Getter for gpa
     * @return
     * GPA of applicant as double
     */
    public double getGpa() {
        return gpa;
    }

    /**
     * Setter for gpa
     * @param gpa
     * Double that is applicants gpa
     */
    public void setGpa(double gpa) {
        if (gpa < 0.0) {
            throw new IllegalArgumentException("GPA cannot be negative");
        }
        this.gpa = gpa;
    }

    /**
     *Getter for applicant college
     * @return
     * College of applicant as string
     */
    public String getCollege() {
        return college;
    }

    /**
     * Setter for applicants college
     * @param college
     * String that is applicants college
     */
    public void setCollege(String college) {
        this.college = college;
    }

    /**
     *Getter for String of skills
     * @return
     * String version of array of skills of applicant as a string
     */
    public String getSkillsString() {
        int emptyCount = 0;
        for(int i = 0; i < skills.length; i++){
            if(skills[i]==null){
                emptyCount++;
            }else if(skills[i].equals("")){
                emptyCount++;
            }
        }
        if(emptyCount == skills.length){
            return "";
        }
        if(swappedSkill == false) {
            for (int i = 0; i < skills.length / 2; i++) {
                String temp = skills[skills.length - i - 1];
                skills[skills.length - i - 1] = skills[i];
                skills[i] = temp;
            }
            swappedSkill = true;
        }
        String skillString = "";
        for(int i = 0; i < skills.length; i++){
            if(skills[i]!=null) {
                if(!(skills[i].equals(""))) {
                    skillString += skills[i] + ",";
                }
            }
        }
        skillString = skillString.replace("[", "").replace("]", "").replace("null", "").replace(" , ", "").replace(",,","").replace(", ",",");

        if(skillString.charAt(0) == ','){
            skillString= skillString.substring(1);
        }
        if(skillString.charAt(skillString.length()-1) == ','){
            skillString= skillString.substring(0, skillString.length()-1);
        }
        return skillString;
    }

    /**
     *Getter of Array of Skills of Applicant
     * @return
     * String Array of skills of applicant as array
     */
    public String[] getSkills() {
        return skills;
    }

    /**
     * Setter for array of skills of applicant
     * @param skills
     * String Array of applicants skills
     */
    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    /**
     *Creates clone of Applicant
     * @return
     * Clone of applicant
     */
    @Override
    public Object clone(){
        try {
            Applicant clone = (Applicant) super.clone();
            clone.companyName = this.companyName.clone();
            clone.skills = this.skills.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    /**
     *Compares Applicant to Object
     * @param obj
     * Applicant to compare to
     * @return
     * Returns true if applicant has the same value for all parameters as applicant obj, else return false
     */
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Applicant applicant = (Applicant) obj;
        boolean skillsEqual = false;
        if(getSkillsString().equals(applicant.getSkillsString())){
            skillsEqual = true;
        }
        boolean companyNameEqual = false;
        if(getCompanyNameString().equals(applicant.getCompanyNameString())){
            companyNameEqual = true;
        }
        return Double.compare(applicant.gpa, gpa) == 0 &&
                companyNameEqual &&
                applicantName.equals(applicant.applicantName) &&
                college.equals(applicant.college) &&
                skillsEqual;
    }

    /**
     * Gives a list of applicants attributes
     * @return
     * Lists all details of applicants
     */
    public String toString(){
        return "Applicant Name: " + getApplicantName() + "\n" +
                "Applicant Applying From: " + getCompanyNameString() + "\n" +
                "Applicant GPA: " + getGpa() + "\n" +
                "Applicant College: " + getCollege() + "\n" +
                "Applicant Skills: " + getSkillsString() + "\n";
    }
}
