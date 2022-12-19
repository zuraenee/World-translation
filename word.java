public class Word
{
    private String english; // English Word
    private String frenchF; // French Word (Feminine form)
    private String frenchM; // // French Word (Masculine form)
    
    /**
     * English work converted to caps
     */
    public Word(String englishIn, String frenchFIn, String frenchMIn)
    {
       english = englishIn.toUpperCase();
       frenchF = frenchFIn;
       frenchM = frenchMIn;
    }

    public String getEnglish() {
        return english;
    }
    
    public String getFrenchM() {
        return frenchM;
    }
    
    public String getFrenchF() {
        return frenchF;
    }
}
