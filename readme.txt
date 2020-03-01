Autor: Marcin Różański 293145
Problem:

Kręgielnia
Dla kręgielni należy opracować algorytm rezerwacji torów. Rezerwacja określa, na jak długo potrzebny będzie pojedynczy tor (których w kręgielni jest n) oraz przedział czasowy, 
w którym musi być ona zrealizowana. Zbiór rezerwacji dla danego dnia jest znany z góry - zadanie polega na ułożeniu planu obłożenia torów maksymalizującego ich użycie.
Należy porównać rozwiązanie dokładne z heurystyką.

Uruchomienie programu:
Uruchomienie z danymi z pliku: 
	m1>>plik.txt
	plik.txt
	w 1 lini: jaki algorytm(heuristic/brutal)/czas otwarcia(*)/czas zamknięcia/liczba torów	//np heuristic 10:00 17:15 4
	w pozostałych liniach: id rezerwacji(kolejność rosnąca)/czas początku rezerwacji/czas zakonczenia rezerwacji/czas rezerwacji 
	//np 
	1 12:30 12:45 0:15
	2 12:30 13:15 0:30
Uruchomienie z generacją danych:
	m2/jaki algorytm(heuristic/brutal)/czas otwarcia/czas zamknięcia/liczba torów/liczba rezerwacji	//np m2 brutal 9:15 18:45 4 30
Uruchomienie z pomiarem czasu:
	m3/czas otwarcia/czas zamknięcia/liczba torów/liczba rezerwacji/ilość iteracji/wzrost liczby rezerwacji w kolejnej iteracji/ilość instancji na iterację 
	//np m3 8:30 19:00 300 1000 5 1000 10
(*) wszystkie wprowadzane czasy muszą być wielokrotościa kwadransu(8:00, 8:15, 8:30 itd.)
Dane wyjściowe:
	m1/m2 - plik result.txt z wynikiem oraz generowana tabela pokazująca plan
	+m2 - plik generatedData z wygenerowanymi danymi
	m3 - tabela z czasami wykonania
Pliki źródłowe:
Main.java - klasa wywołująca program
Algorithms:
	Algorithm.java - interfejs algorytmu
	Heuristic.java - implementacja rozwiązania heurystycznego
	Brutal.java - implementacja rozwiązania brutalnego
Data:
	DataLoader.java - Wczytywanie danych z pliku dla wywołania m1
	Hour.java - reprezentacją godziny
	Range.java - reprezentacja przedziału czasowego
	Reservation.java - reprezentacja rezerwacji
DisplayData:
	Results.java - tworzenie pliku result.txt z wynikiem dla m1/m2
	Table.java - generacja tabeli z planem dla m1/m2
	TimeTable.java - generacja tabeli z czasami wykonania dla m3
Exits
	Exits.java - wywolywanie funkcji wyjścia z danym komunikatem
GenerateData:
	Generator.java - generator danych dla m2/m3
InputController:
	InputController.java - interfejs kontrolera danych
	InputControllerm1/m2/m3.java - kontroler danych dla danego wywołania
Run:
	Run.java - uruchomienie konktetnej wersji programu
