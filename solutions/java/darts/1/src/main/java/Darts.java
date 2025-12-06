class Darts {
    int score(double xOfDart, double yOfDart) {
       double radius = Math.sqrt(Math.pow(xOfDart,2)+Math.pow(yOfDart,2));
        if(radius<=1.0)
            return 10;
        else if(radius>1.0 && radius<=5.0)
            return 5;
        else if(radius>5.0 && radius<=10.0)
            return 1;
        else
            return 0;
            
    }
}
