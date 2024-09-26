/**
 * @author Vanshdeep Singh
 * Stonybrook ID: 116535948
 * Recitation: 01
 */
public class HiringTable implements Cloneable{
    public static final int MAX_SKILLS = 3;
    public static final int MAX_COMPANIES = 3;
    public static final int MAX_APPLICANTS = 50;
    private int count = 0;
    private Applicant[] table;

    /**
     * Creates a new Applicant array with length of MAX_APPLICANTS
     */
    public HiringTable(){
        table = new Applicant[MAX_APPLICANTS];
    }

    /**
     *Finds and gives details of applicant based on index in HiringTable
     * @param i
     * Index of HiringTable that should be returned
     * @return
     * Applicant that is found at index i of HiringTable
     */
    public Applicant returnsApp(int i){
        Applicant temp = table[i];
        return temp;
    }

    /**
     *Gives amount of Applicants
     * @return
     * Amount of applicants in HiringTable
     */
    public int size(){
        return count;
    }

    /**
     *Adds Applicant to HiringTable
     * @param newApplicant
     * Applicant that should be added to the table
     * @throws FullTableException
     * If HiringTable has an applicant in every slot, throws FullTableException and states that HiringTable is full
     */
    public void addApplicant(Applicant newApplicant) throws FullTableException{
        if (count >= MAX_APPLICANTS) {
            throw new FullTableException("HiringTable is full. Cannot add more applicants.");
        }
        table[count] = newApplicant;
        count++;
    }

    /**
     *Removes Applicant from HiringTable
     * @param name
     * Name of applicant that should be removed
     * @throws ApplicantNotFoundException
     * If no applicant with that name is found, throws ApplicantNotFoundException and states that applicant isn't found.
     */
    public void removeApplicant(String name) throws ApplicantNotFoundException{
        int index = -1;
        for(int i = 0; i < count; i++){
            if(table[i].getApplicantName().equals(name)){
                index = i;
            }
        }
        if (index == -1) {
            throw new ApplicantNotFoundException("Applicant with name " + name + " not found.");
        }
        for(int j = index; j<count; j++){
            table[j] = table[j+1];
        }

        count--;
    }

    /**
     *Gets applicant information from HiringTable based on name
     * @param name
     * String name of applicant that should be found
     * @return
     * Returns Applicant that has the same name as parameter name
     * @throws ApplicantNotFoundException
     * If no Applicant with name is found in HiringTable, throws ApplicantNotFoundException and state applicant not found
     */
    public Applicant getApplicant(String name) throws ApplicantNotFoundException{
        for (int i = 0;i<count;i++){
            if(table[i].getApplicantName().equals(name)){
                return table[i];
            }
        }
        throw new ApplicantNotFoundException("Applicant with name " + name + " not found.");
    }

    /**
     * Prints HiringTable only including elements that match the search criteria
     *
     * @param tables
     * The HiringTable with all applicants
     * @param company
     * Company name to filter by
     * @param skill
     * Skill to filter by
     * @param college
     * College to filter by
     * @param GPA
     * Minimum GPA to filter by
     */
    public static void refineSearch(HiringTable tables, String company, String skill, String college,double GPA){
        System.out.println("Applicant List:");
        System.out.printf("%-25s %-25s %-25s %-25s %-25s\n", "Companies", "Name" , "GPA", "College", "Skills");
        System.out.println("-----------------------------------------------------------------------------------------");

       boolean skillusedJava = false;
        if(((skill.equals("java"))||(skill.equals("Java")))==true){
            skillusedJava = true;
        }

        for(int i = 0; i<tables.size(); i++){
            Applicant applicant = tables.returnsApp(i);
            if(skillusedJava==false) {
                if (applicant.getCompanyNameString().contains(company) &&
                        applicant.getSkillsString().contains(skill) &&
                        applicant.getCollege().contains(college) && applicant.getGpa() >= GPA) {
                    System.out.printf("%-25s %-25s %-25.2f %-25s %-25s\n", applicant.getCompanyNameString(), applicant.getApplicantName(), applicant.getGpa(), applicant.getCollege(), applicant.getSkillsString());
                }
            }else{
                if (applicant.getCompanyNameString().contains(company) &&
                        applicant.getSkillsString().contains(skill) &&
                        applicant.getCollege().contains(college) && applicant.getGpa() >= GPA) {
                    if(!(applicant.getSkillsString().contains("Javascript"))) {
                        System.out.printf("%-25s %-25s %-25.2f %-25s %-25s\n",
                                applicant.getCompanyNameString(),
                                applicant.getApplicantName(),
                                applicant.getGpa(), applicant.getCollege(),
                                applicant.getSkillsString());
                    }
                }
            }

        }
    }

    /**
     * Creates clone of HiringTable
     * @return
     * Clone of HiringTable
     */
    @Override
    public Object clone(){
        try {
            HiringTable clone = (HiringTable) super.clone();
            clone.table = new Applicant[MAX_APPLICANTS];
            for (int i = 0; i < count; i++) {
                clone.table[i] = (Applicant) table[i].clone();
            }
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    /**
     * Prints HiringTable in tabular form
     */
    public void printApplicantTable() {
        System.out.println("Applicant List:");
        System.out.printf("%-25s %-25s %-25s %-25s %-25s\n", "Companies", "Name" , "GPA", "College", "Skills");
        System.out.println("-------------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < count; i++) {
            Applicant applicant = table[i];
            System.out.printf("%-25s %-25s %-25.2f %-25s %-25s\n",
                    applicant.getCompanyNameString(),
                    applicant.getApplicantName(),
                    applicant.getGpa(), applicant.getCollege(),
                    applicant.getSkillsString());
        }
    }
}
