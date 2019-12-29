# DuplicatedProducts
Application that shows "transaction list's" duplicated products and their quantities using Allegro Selling Manager ms.allegro.pl csv file.
It also returns list of products that contains "shimano" string;

INSTRUKCJA UŻYTKOWNIKA

1. Przygotowanie pliku csv w menedżerze sprzedaży allegro ms.allegro.pl
a. logujemy się do ms.allegro.pl
b. wchodzimy do sekcji Dodatkowe Opcje -> Eksportuj Dane
c. 
- wybieramy okres dla naszych transakcji
- wybieramy sekcję transakcji
- wybieramy format pliku na CSV
- lista parametrów musi zawierać w kolejności : Numer transakcji, Nazwa oferty, Liczba zakupionych sztuk
- wybrane parametry możemy zapisać do szablonu

d. naciskamy eksportuj dane

2. Uruchamiamy program Duplicated Products
a. naciskamy przycisk Select File
b. wyszukujemy nasz plik utworzony w menedżerze sprzedaży i otwieramy go
c. naciskamy przycisk RUN

3. Efekt działania programu zobaczymy w wyskakującym okienku oraz w nowo powstałym pliku o nazwie duplicatedProducts.txt.
Plik ten znajdować się będzie w katalogu c:\duplicatedProductsOutput. W tym samym katalogu znajdować się będzie
plik shimanoProducts.txt, zawierający wszystkie produkty marki shimano.

Author : damazy5@wp.pl
