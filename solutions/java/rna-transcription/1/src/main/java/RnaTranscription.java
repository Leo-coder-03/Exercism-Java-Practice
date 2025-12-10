class RnaTranscription {

    String transcribe(String dnaStrand) {
        if(dnaStrand== null || dnaStrand.isEmpty())
            return "";
        String rnaStrand ="";
        for(char c:dnaStrand.toCharArray())
            {
                if(c == 'G')
                    rnaStrand+='C';
                else if(c == 'C')
                    rnaStrand+='G';
                else if(c == 'T')
                    rnaStrand+='A';
                else
                    rnaStrand+='U';
            }
        return rnaStrand;
    }

}
