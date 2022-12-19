import java.util.Scanner;
public class LookUpFrenchWord
{
    // Array of words, each Word has English and French equivalents
    Word [] englishToFrench;
    // Array for hashing is size 14, 40% larger than the number of words
    // to reduce collisions
    final int TABLESIZE = 14;

    private int hash(String word, int offset) {
        word = word.toUpperCase();
        int total = 0;
        for (int i=0; i<word.length(); i++) {
            total += word.charAt(i);
        }
        return (total + offset) % TABLESIZE;
    }
    
    public LookUpFrenchWord()
    {
        // Create the array of the English words and their French equivalents
        
        englishToFrench = new Word[TABLESIZE];
        int position, count;
        // Data to load into englishToFrench array
        String englishAnimals[] = {"rabbit","cat","dog","sheep","lion","tiger","duck","pigeon","fox","pig"};
        String frenchAnimalsF[] = {"lapine","chatte","chienne","brebis","lionne","tigresse","cane","pigeonne","renarde","truie"};// French names in same order
        String frenchAnimalsM[] = {"lapin","chat","chien","mouton","lion","tigre","canard","pigeon","renard","porc"};
        
        // Make every entry in englishToFrench array null to show it is empty
        for (int i = 0; i < TABLESIZE; i++) {
            englishToFrench[i] = null;
        }
        
        // Work through the word lists
        for (int i = 0; i < englishAnimals.length; i++) {
            
            // Re-hash the current word until an empty space is found
            count = 0;
            while (englishToFrench[hash(englishAnimals[i], count)] != null) {    
                // Print words when they have to be re-hashed
                System.out.println("..... " + count + " Trying " + englishAnimals[i] + " again");
                count++;
            }
            
            // Put word in free position
            position = hash(englishAnimals[i], count);
            englishToFrench[position] = new Word(englishAnimals[i], frenchAnimalsF[i],frenchAnimalsM[i]);
            
            // Output position of Word
            System.out.println(englishAnimals[i] + " " + position);
        }
    }
    
    public String getFrenchWord(String englishWord) {
        // Returns French equivalent of English word
        // If English word is present the translation should be returned in the form
        // "Feminine French Word(F), Masculine French Word(M)"
        // If the English is not present return
        // "Sorry word not in table"
        
        int step = 0, position;
        englishWord = englishWord.toUpperCase();
        String frenchWord="";
        
        // Hash the English word
        position = hash(englishWord, step);
        Word currentWord = englishToFrench[position];
        
        // Keep rehashing the English word while
        // There is no empty space and the word has not been found and the whole of the table has not been looked through
        while (currentWord != null && ! currentWord.getEnglish().equals(englishWord) &&  step != TABLESIZE) {
                for (int i=0; i<englishWord.length; i++){
                    i = hash(englishWord, step);
                    currentWord = englishToFrench[i];
                }
        }
        
        
        if (englishWord != null){
            System.out.println("Feminine French Word(F), Masculine French Word(M)");
        }
        else{
            System.out.print("Sorry word not in table");
        }
        
        return frenchWord;
    }
    
    public void printWordList() {
        // Prints all the items in the word list from position 0 onwards
        // If there is no Word (i.e. the object is null), "Empty" should be printed
        System.out.println("Word list contents ");
        if (position != null){
            System.out.println(frenchWord[position]);
        }
        else{
            System.out.print("the object is null);
        }

        
    }
    
    
    public static void main() {
        // Clear screen
        System.out.print("\u000C");
        Scanner kbr = new Scanner(System.in);
        LookUpFrenchWord wordBook = new LookUpFrenchWord();
        String englishWord, anotherGo="Y";
        while (anotherGo.toUpperCase().equals("Y")) {
            System.out.print("Enter word to translate: ");
            englishWord = kbr.nextLine();
            
            /*
             * Call the getFrenchWord method from the wordBook object
             * to translate the English word input into its French equivalent
             */
            getFrenchWord();
            
            System.out.print("Do you want to translate another word Y/N ");
            anotherGo = kbr.nextLine();
            System.out.println("-------------------------------------------------------");
        }
        wordBook.printWordList();
    }
 
}
