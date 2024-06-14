public class MathInteger {
    public static void main(String[] args) {
        System.out.println(sqrt_halbierung(10));
        //System.out.println(sqrt_fort(17));
    }
    public static int iquot_fortschaltung(int n, int m) {
        if (n < 0 || m <= 0) throw new IllegalArgumentException();
        int u = 0;
        int h = m;
        assert(n >= 0 && m > 0 && u == 0);
        assert (u * m <= n && h == (u + 1) *m );
        while (h <= n) {
            assert(u * m <= n && h <= n && h == (u + 1) * m);
            u = u + 1;
            assert(u * m <= n && h == u * m);
            h = h + m;
            assert(u * m <= n && h == (u + 1) * m);
        }
        assert(u * m <= n && h > n && h == (u + 1) * m);
        assert(u * m <= n && (u+1)*m >n);
        return u;
    }
    public static int isqrt(int n) {
        if (n < 0) throw new IllegalArgumentException();
        int k = 0;
        assert(n >= 0 && k == 0);//V
        assert(k * k <= n);//I
        while ((k + 1)*(k + 1) <= n) {
            assert(k * k <= n && (k + 1)*(k + 1) <= n);//I && B
            k++;
            assert(k * k <= n); //I
        }
        assert(k * k <= n && (k + 1)*(k + 1) > n); //I && !B = N
        return k;
    }
    public static int log_halbierung(int a, int n) {
        if (a <= 0 || n <= 0) throw new IllegalArgumentException();
        int u = 0;
        int i = n + 1;
        assert(a > 0 && n > 0 && u == 0 && i == n + 1); //V
        assert (Math.pow(a,u) <= n && u <= i - 1 && Math.pow(a,i) > n); //I
        while (u < i - 1) {
            assert (Math.pow(a,u) <= n && u <= i - 1 && Math.pow(a,i) > n && u < i - 1); //I && B
            int k = (u + i)/ 2;
            if (Math.pow(a,k) <= n)
                u = k;
            else
                i = k;
            assert (Math.pow(a,u) <= n && u <= i - 1 && Math.pow(a,i) > n); //I
        }
        assert (Math.pow(a,u) <= n && u <= i - 1 && Math.pow(a,i) > n && u >= i - 1); //I && B
        assert (Math.pow(a,u) <= n && Math.pow(a,u + 1) > n); //N
        return u;
    }
    public static int quot_halbierung(int n, int m) {
        if (n < 0 || m <= 0) throw new IllegalArgumentException();
        int u = 0;
        int i = n + 1;
        assert(n >= 0 && m > 0 && u == 0 && i == n + 1);
        assert (u*m <= n && n < i*m && u <= i -1); // I
        while (u < i -1) {
            assert (u*m <= n && n < i * m && u <= i - 1 && u < i-1); // I && B
            int a = (u + i) / 2;
            if (a* m <= n)
                u = a;
            else
                i = a;
            assert (u*m <= n && n < i*m && u <= i-1); // I
        }
        assert(u *m <= n && n < i *m && u <= i-1 && u >= i -1); //I &&!B
        assert(u *m <= n && (u+1)* m > n); // N
        return u;
    }
    public static int sqrt_halbierung(int n) {
        if (n < 0) throw new IllegalArgumentException();
        int u = 0;
        int i = n + 1;
        assert(n >= 0 && u == 0 && i == n + 1);
        assert (u*u <= n && n < i*i && u <= i -1); // I
        while (u < i -1) {
            assert (u*u <= n && n < i*i && u <= i - 1 && u < i-1); // I && B
            int a = (u + i) / 2;
            if (a* a <= n)
                u = a;
            else
                i = a;
            assert (u*u <= n && n < i*i && u <= i-1); // I
        }
        assert(u *u <= n && n < i*i && u <= i-1 && u >= i -1); //I &&!B
        assert(u *u <= n && u == i -1 && n < (u + 1)*(u+1)); // N  && n < i*i
        //assert(false);
        return u;
    }
    public static double hilf(double x, int n) {
        if (n < 0) throw new IllegalArgumentException();
        double ergebnis = 1;
        for (int i = n; i >= 1; i--) {
            ergebnis = ergebnis * (x/i);
        }
        return ergebnis;
    }
    public static double exp1(double epsilon) {
        if (epsilon <= 0) throw new IllegalArgumentException();
        int n = 0;
        double summe = 0;
        do {
            summe += hilf(1,n);
            n++;
        } while (2* hilf(1,n) >= epsilon);
        return summe;
    }
    public static double cos(double x, double epsilon) {
        if (epsilon <= 0) throw new IllegalArgumentException();
        int n = 0;
        double summe = 0;
        //summe += hilf(x,2*n+1)*Math.pow(-1,n);
        while (hilf(x,n+2) >= epsilon) {
            summe += hilf(x,2*n)*Math.pow(-1,n);
            n++;
        }
        return summe;
    }
    public static double sinus(double epsilon,double x) {
        if (epsilon <= 0) throw new IllegalArgumentException();
        int n = 0;
        double summe = 0;
        //summe += hilf(x,2*n+1)*Math.pow(-1,n);
        while (hilf(x,n+2)*Math.pow(x,n) >= epsilon){
            summe += hilf(x,2*n+1)*Math.pow(-1,n);
            n++;
        }
        return summe;
    }
    public static double newton_sqrt(double epsilon,double i) {
        if (i < 0) throw new IllegalArgumentException();
        double n = 1;
        do  {
            n = n - (n * n - i)/(2 * n);
        } while (n * n-i >= epsilon);
        return n;
        //n*n soll = x ->      f(x) = n*n -x         = 0 --> 2n
    }
    public static double newton( double i, double eps ) {
         if(i < 0) throw new IllegalArgumentException ();
         double xneu = 1;
         double n;
         do {
            n = xneu;
            xneu = n - (( n * n - i) / (2* n ));
             } while ( Math .abs(n -xneu) > eps );
         return xneu;
         }
    public static double sqrt_fort(double n) {
        if (n < 0) throw new IllegalArgumentException();
        int k = 0;
        int h = 1;
        assert(n >= 0 && k == 0 && h == 1);//V
        assert(k * k <= n && h == (k + 1)*(k + 1));//I
        while (h <= n) {
            assert(k * k <= n && h == (k + 1)*(k + 1) && h <= n);//I && B
            k++;
            assert(k * k <= n && h == k*k);
            h = h + k + k +1;
            assert(k * k <= n && h == (k + 1)*(k + 1));//I
        }
        assert(k * k <= n && h == (k + 1)*(k + 1) && h > n); //I && !B = N
        assert(k * k <= n && (k + 1)*(k + 1) > n);
        return k;
    }
    public static int ln(int a, int n) {
        if (a <= 0 || n <= 0) throw new IllegalArgumentException();
        int x = 0;
        assert(a > 0 && n > 0 && x == 0); //V
        assert(Math.pow(a,x) <= n);//I
        while (Math.pow(a,x + 1) <= n) {
            assert(Math.pow(a,x) <= n && Math.pow(a,x + 1) <= n); //I && B
            x++;
            assert(Math.pow(a,x) <= n);//I
        }
        assert(Math.pow(a,x) <= n && Math.pow(a,x + 1) > n);//I && !B = N  a^x <= n < a^(x+1)
        return x;
    }
    public static double ln_fort(int a, int n) {
        if (a <= 0 || n <= 0) throw new IllegalArgumentException();
        int x = 0;
        double h = a;
        assert(a > 0 && n > 0 && x == 0); //V
        assert(Math.pow(a,x) <= n && h == Math.pow(a,x + 1));//I
        while (h <= n) {
            assert(Math.pow(a,x) <= n && h == Math.pow(a,x + 1) && h <= n); //I && B
            x++;
            assert(Math.pow(a,x) <= n && h == Math.pow(a,x));
            h = h * a;
            assert(Math.pow(a,x) <= n && h == Math.pow(a,x + 1));//I
        }
        assert(Math.pow(a,x) <= n && h == Math.pow(a,x + 1) && h > n);
        assert(Math.pow(a,x) <= n && Math.pow(a,x + 1) > n);//I && !B = N
        return x;
    }
    public static int iquot(int n, int m) {
        if (n < 0 || m <= 0) throw new IllegalArgumentException();
        int u = 0;
        assert(n >= 0 && m > 0 && u == 0);
        assert (u * m <= n);
        while ((u + 1) * m <= n) {
            assert(u * m <= n && (u + 1) * m  <= n);
            u = u + 1;
            assert(u * m <= n);
        }
        assert(u * m <= n && (u + 1) * m >n);
        return u;
    }
    public static double heron(double x,double epsilon) {
        double a = 1;
        double b = x;
        while (Math.abs(a - b) > epsilon) { // diff zwischen alt und neuem Wert
            a = (a + b)/2;
            b = x/a;
        }
        return a;
    }
    public static double log_newton(int a,int n,double epsilon) {
        double x = 1;
        double pre = 0;
        double hilf;
        while (Math.abs(Math.pow(a,x) - n) > epsilon) {
            hilf = x;
            x = x - (Math.pow(a,x) - n) * (pre - x)/(Math.pow(a,pre) - Math.pow(a,x));
            pre = hilf;
        }
        return x;
        //a^x - n = 0    f'(x) = a^x * ln(a)
    }
    public static double pi(double epsilon) {
        if (epsilon <= 0) throw new IllegalArgumentException();
        double x = 0;
        double alt = 3;
        int k = 0;
        while (Math.abs(x - alt) > epsilon) {
            alt = x;
            x += Math.pow(-1,k)/(2 * k + 1);
            k++;
        }
        return 4 * x;
    }

}