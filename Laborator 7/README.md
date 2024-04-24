Graful dat este unul bipartit G = (V, E).
Un perfect matching într-un graf este un set de muchii în care fiecare nod al grafului este incident cu exact o muchie din set.

(I) Daca graful are un perfect matching, inseamna ca fiecare nod este continut de o muchie din match. In acest caz, indiferent de mutarea jucatorului care incepe, adversarul poate alege întotdeauna nodul conectat la nodul jucatorului care incepe (nod conectat de muchia care apare in perfect matching). 
Acest lucru asigura ca adversarul poate face intotdeauna o mutare, fortand in cele din urma jucatorul care începe să nu mai poată face mutari.
Prin urmare, am stabilit că jucitorul care incepe NU poate avea o stratie de castigt cand graful are perfect matching.

(II) Daca graful nu are perfect matching => exista cel putin un nod i care sa nu aiba o muchie in perfect match care il contine. 
In cazul in care V-{i} = perfect mathing => Jucatorul 1 ar avea ca strategie de castig alegerea nodului i, deoarece prin alegerea lui, jucatorul 2 ca fi obligat sa aleaga un nod dintr- un graf care contine un pefect mathing (demonstrat in I ca va duce la pierderea jucatourlui 2). => jucatorul 1 ar putea avea o stategie de castig daca graful nu are perfect matching

In concluzie, din (I) si (II) =>  jujcatorul 1 ar putea avea o strategie de castig doar daca graful nu are un perfect mathing/
