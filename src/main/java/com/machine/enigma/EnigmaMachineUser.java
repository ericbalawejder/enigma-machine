package com.machine.enigma;

class EnigmaMachineUser {

    public static void main(String[] args) {
        Rotor r1 = new Rotor("QWERTYUIOPLKJHGFDSAZXCVBNM");
        Rotor r2 = new Rotor("ZAQWSXCDERFVBGTYHNMJUIKLOP");
        Rotor r3 = new Rotor("PLOKMIJNUHBYGVTFCRDXESZWAQ");
        Reflector rf = new Reflector("NPKMSLZTWQCFDAVBJYEHXOIURG");

        EnigmaMachine em = new EnigmaMachine(r1, r2, r3, rf);

        String s = "AAAAAAAAAAAAAAAAAAAAAAAAAAA";
        System.out.println("uncoded: " + s);

        em.setRotors(5, 9, 14);
        String encodedS = em.encodeLine(s);
        System.out.println("encoded: " + encodedS);

        em.setRotors(5, 9, 14);
        String decodedEncodedS = em.encodeLine(encodedS);
        System.out.println("decoded: " + decodedEncodedS);
    }
}
