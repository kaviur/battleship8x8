package com.epam.rd.autotasks;

public class Battleship8x8 {
    private final long ships;
    private long shots = 0L;
    private String strShots;
    private String strShips;

    public Battleship8x8(final long ships) {
        this.ships = ships;
        convertingToString();
    }

    public void convertingToString(){
        strShots = Long.toBinaryString(shots);
        strShots = fillingZeros(strShots,63);
        strShips = Long.toBinaryString(ships);

        int leadingZeros = Long.numberOfLeadingZeros(ships);
        if( leadingZeros > 0 ) {
            strShips = fillingZeros(strShips,leadingZeros);
        }
    }

    public boolean shoot(String shot) {
        int index = (shot.charAt(1)-'1')*8+(shot.charAt(0)-'A');
        strShots = saveShot(strShots,index,'1');

        if( strShips.charAt(index) == '1' ){
            return true;
        }
        return false;
    }

    public String state() {
        StringBuilder sb = new StringBuilder();

        for (int i=0; i<strShips.length(); i++){
            if( i % 8 == 0 ){
                sb.append('\n');
            }
            if(strShips.charAt(i) == '1' && strShots.charAt(i) == '1'){
                sb.append('☒');
            } else if (strShips.charAt(i) == '1') {
                sb.append('☐');
            } else if (strShots.charAt(i) == '1') {
                sb.append('×');
            }
            else {
                sb.append('.');
            }
        }
        return sb.toString();
    }

    public String fillingZeros(String str,int numOfZeros){
        StringBuilder sb = new StringBuilder();
        for ( int i=0; i<numOfZeros; i++){
            sb.append('0');
        }
        return sb.append(str).toString();
    }

    public String saveShot(String str, int index, Character ch){
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(index, ch);
        str = sb.toString();
        return str;
    }
}
