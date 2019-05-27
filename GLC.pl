:-include('Lugares.pl').
:-include('Arcos.pl').
:-style_check(-singleton). /*Establece que solo se inicie una vez el programa y no varias veces al mismo tiempo*/

init():-sendRead(). /*Inicia el programa*/

% Envia un mensaje de bienvenida, lee lo ingresado por el usuario y lo
% convierte a string (read_line_to_string), ademas le aplica un lower
% case (string_lower) y lo almacena en una lista (atomic_list_concat),
% luego llama a interpreter pasandole L1 que es la lista que contiene la
% oracion que ingreso el usuario.
sendRead():-write('DrWazeLog: Bienvenido a WazeLog la mejor lÛgica de llegar a su destino. Por Favor indÌqueme donde se encuentra.')
,nl
,write('Usuario: ')
,read_line_to_string(user_input, Cs)
,string_lower(Cs, A)
,atomic_list_concat(L1,' ',A)
,interpreter(L1).


% Recibe la oracion ingresada por el usuario y q1 valida si corresponde
% a la pregunta 1 y ademas si tiene una gramatica correcta. De ser
% asi entonces devuelve el lugar de origen del usuario y llama a
% sendRead2 pasandole como parametro el lugar de origen. Si no cumple
% con la gramatica de la pregunta 1, entonces envia un mensaje de error
% y le solicita que ingrese de nuevo su respuesta, ademas hace una
% recomendacion de como debe ser la misma.
interpreter(Oracion):-nl,
    (q1(Oracion,Lugar)->nl,sendRead2(Lugar),nl;
    not(q1(Oracion,Lugar))->write('DrWazeLog: '),write('No se ha entendido la indicacion o el lugar no existe, por favor digite de nuevo su origen (La forma mas segura es -Estoy en (localizacion)-')
                     ,nl
                     ,write('Usuario: ')
                     ,read_line_to_string(user_input, Cs)
                     ,string_lower(Cs, A)
                     ,atomic_list_concat(L1,' ',A)
                     ,interpreter(L1)).


% Recibe el lugar de origen del usuario, a partir de eso le pregunta al
% usuario por su destino final, lee lo ingresado por el
% usuario y lo convierte a string (read_line_to_string), ademas le
% aplica un lower case (string_lower) y lo almacena en una lista
% (atomic_list_concat),obtiene el origen de la lista que lo lleva para
% no seguir acarreandolo como lista y llama a interpreter2 pasandole
% como parametros el origen y la lista que contiene la oracion ingresada
% por el usuario.
sendRead2(OrigenL):-write('DrWazeLog: Muy bien, øCu·l es su destino?')
,nl
,write('Usuario: ')
,read_line_to_string(user_input, Cs)
,string_lower(Cs, A)
,atomic_list_concat(L1,' ',A)
,sacar(OrigenL,Origen),interpreter2(Origen,L1).

% Recibe como parametros una lista y retorna el elemento que esta
% contenido en la misma.
sacar([X],X).

% Recibe el lugar de origen del usuario y la respuesta de este al
% destino que quiere ir, de este modo a q2 se le pasa como parametro la
% oracion y este valida si es correcta y si corresponde a la pregunta 2.
% De ser asi entonces retorna el lugar de destino en una lista y llama a
% sendRead3, ademas le pasa como parametros el origen del usuario y
% el lugar de destino. Si la respuesta del usuario no cumple con la
% gramatica de la pregunta 2 entonces envia un mensaje de error y le
% solicita que ingrese de nuevo su respuesta, ademas hace una
% recomendacion de como debe ser la misma.
interpreter2(Origen,Oracion):-nl,
    (q2(Oracion,LugaresDestino)->sendRead3(Origen,LugaresDestino);
    not(q2(Oracion,LugaresDestino))->write('No se ha entendido la indicacion o el lugar no existe, por favor digite de nuevo su destino (La forma mas segura es -En (destino)-')
                     ,nl
                     ,write('Usuario: ')
                     ,read_line_to_string(user_input, Cs)
                     ,string_lower(Cs, A)
                     ,atomic_list_concat(L1,' ',A)
                     ,interpreter2(Origen,L1)).

% Recibe el lugar de origen del usuario y la lista de lugares destinos
% que desea visitar, ademas procede a preguntarle al usuario si desea
% pasar a un lugar intermedio antes de llegar a su destino final. Lee lo
% ingresado por el usuario y lo convierte a string
% (read_line_to_string), ademas le aplica un lower case (string_lower) y
% lo almacena en una lista (atomic_list_concat). Llama a interpreter3 y
% le pasa como parametros el origen del usuario, la lista de los
% destinos que quiere visitar y la respuesta del usuario a la pregunta
% realizada.
sendRead3(Origen,LugaresDestino):-nl,write('DrWazeLog: '),write('Excelente, øTiene alg˙n destino intermedio?')
,nl
,write('Usuario: ')
,read_line_to_string(user_input, Cs)
,string_lower(Cs, A)
,atomic_list_concat(L1,' ',A)
,interpreter3(Origen,LugaresDestino,L1).


% Recibe el origen del usuario, la lista de los destinos que quiere
% visitar y una lista de la respuesta del usuario. Valida si la
% respuesta del usuario es 'no' lo cual indica que no desea visitar
% ningun lugar intermedio (ninguno, 1 o mas), llama a validaRuta y le
% pasa como parametros el origen del usuario y la lista de destinos que
% desea visitar. Si recibe algo que no sea 'no' valida si la gramatica
% es correcta y si corresponde a la pregunta 3, de ser asi, retorna
% el lugar intermedio al que se desea visitar y llama a sendRead4
% pasandole como parametros el origen del usuario, y los lugares
% destino. Si no cumple con la gramatica correcta envia un mensaje de
% error y le indica una forma adecuada de responder.
interpreter3(Origen,LugaresDestino,Oracion):-nl,
    (no(Oracion)->validaRuta(Origen,LugaresDestino));
    q3(Oracion,X)->sacar(X,LugarPasar),sendRead4(Origen,LugaresDestino,LugarPasar);
    not(q3(Oracion,X))->write('No se ha entendido la indicacion o el lugar no existe, por favor digite de nuevo su destino (La forma mas segura es -Tengo que pasar al (establecimiento)-')
                     ,nl
                     ,write('Usuario: ')
                     ,read_line_to_string(user_input, Cs)
                     ,string_lower(Cs, A)
                     ,atomic_list_concat(L1,' ',A)
                     ,interpreter3(Origen,LugaresDestino,L1).

% Recibe el lugar de origen del usuario y la lista de lugares destinos
% que desea visitar, ademas del lugar al que desea realizar una
% parada intermedia, luego procede a preguntarle al usuario cual es ese
% destino intermedio al que desea ir. Lee lo ingresado por el usuario y
% lo convierte a string (read_line_to_string), ademas le aplica un lower
% case (string_lower) y lo almacena en una lista (atomic_list_concat),
% Llama a interpreter3 y le pasa como parametros el origen del usuario,
% la lista de los destinos que quiere visitar y la respuesta del usuario
% a la pregunta realizada.
sendRead4(Origen,LugaresDestino,LugarPasar):-nl,write('DrWazeLog: øCu·l '),write(LugarPasar),write('?')
,nl
,write('Usuario: ')
,read_line_to_string(user_input, Cs)
,string_lower(Cs, A)
,atomic_list_concat(L1,' ',A)
,interpreter4(Origen,LugaresDestino,L1).

% Recibe el origen del usuario, la lista de los destinos que quiere
% visitar y una lista de la respuesta del usuario. Valida si la
% respuesta del usuario corresponde a un lugar intermedio en la
% base de datos, de ser asi entonces llama a sendRead5 y le pasa el
% origen del usuario, la lista de los lugares destinos y el lugar al que
% se desea realizar un parada intermedia. Si la respuesta del usuario no
% cumple con la gramatica de la pregunta 4 entonces envia un mensaje de
% error y le solicita que ingrese de nuevo su respuesta, ademas hace una
% recomendacion de como debe ser la misma.
interpreter4(Origen,LugaresDestino,Oracion):-nl,
       q4(Oracion,LugarPasarA)->sacar(LugarPasarA,LugarPasar),sendRead5(Origen,LugaresDestino,LugarPasar);
       not(q4(Oracion,LugarPasar))->write('No se ha entendido la indicacion o el lugar no existe, por favor digite de nuevo su lugar de destino intermedio (La forma mas segura es -Me gustaria (establecimiento)-')
                     ,nl
                     ,write('Usuario: ')
                     ,read_line_to_string(user_input, Cs)
                     ,string_lower(Cs, A)
                     ,atomic_list_concat(L1,' ',A)
                     ,interpreter4(Origen,LugaresDestino,L1).

% Recibe el origen del usuario y la lista de los destinos que desea
% visitar, si existe una ruta para visitar todos los destinos deseados
% retorna la ruta que este debe seguir, al realizar las paradas inicia
% de nuevo en ese destino y busca el siguiente hasta llegar al final,
% finalmente le indica la distancia que hay al tomar esa ruta.
validaRuta(Origen,Lugares):-get_whole_path(Lugares,Origen,X,Y),!,write('DrWazeLog: Su ruta es: '),printPath(X),write(' con una
distancia de: '),write(Y),write(' km.'),nl,write('Muchas gracias por su preferencia. °Hasta la prÛxima!').
% Si no encuentra una ruta a los destinos deseados, envia un mensaje de
% error y vuelve el inicio del programa para que intente de nuevo.
validaRuta(Origen,Lugares):-(not(get_whole_path(Lugares,Origen,X,Y))),write('DrWazeLog: No se encuentra ruta alguna para visitar todos los destinos deseados, por favor intente de nuevo.'),nl,sendRead(),nl.

% Recibe una lista con los destinos a visitar y los va imprimiendo
% (mostrando) hasta que la lista sea vacia lo que indica que ya no hay
% mas destinos.
printPath([]).
printPath([X|Resto]):-write(X),write(', '),printPath(Resto).

% Recibe como parametros el origen del usuario, la lista de los
% destinos a visitar y el lugar al que desea realizar una parada
% intermedia, entonces procede a preguntarle al usuario donde se
% encuentra el lugar al que desea ir como destino intermedio. Lee lo
% ingresado por el usuario y lo convierte a string
% (read_line_to_string), ademas le aplica un lower case (string_lower) y
% lo almacena en una lista (atomic_list_concat),Llama a interpreter4 y
% le pasa como parametros el origen del usuario, la lista de los
% destinos a visitar y la lista que contiene la respuesta del usuario.
sendRead5(Origen,LugaresDestino,LugarPasar):-nl,write('DrWazeLog: øDÛnde se encuentra '),write(LugarPasar),write('?')
,nl
,write('Usuario: ')
,read_line_to_string(user_input, Cs)
,string_lower(Cs, A)
,atomic_list_concat(L1,' ',A)
,interpreter5(Origen,LugaresDestino,L1).

% Recibe como parametros el origen del usuario, la lista de los lugares
% destinos y la resuesta del usuario. Valida si la respuesta dada
% corresponde a la pregunta 5 y si su gramatica es correcta, de ser asi,
% agrega el destino al que el usuairo quiere visitar primero antes del
% destino final y lo agrega al inicio de la lista de destinos por
% visitar ya que esta lista funciona con la metodologia LILO (Last In,
% Last Out), luego llama a sendRead3 y le pasa como parametros el
% origen del usuario y la lista actualizada de los destinos a visitar.
% Si no cumple con la gramatica correcta envia un mensaje de error y le
% indica una forma adecuada de responder.
interpreter5(Origen,LugaresDestino,Oracion):-nl,
    (q5(Oracion,X)->append(X,LugaresDestino,DestinosActualizados),sendRead3(Origen,DestinosActualizados);
    not(q5(Oracion,X))->write('No se ha entendido la indicacion o el lugar no existe, por favor digite de nuevo su destino intermedio (La forma mas segura es: Se localiza en (lugar)-')
                     ,nl
                     ,write('Usuario: ')
                     ,read_line_to_string(user_input, Cs)
                     ,string_lower(Cs, A)
                     ,atomic_list_concat(L1,' ',A)
                     ,interpreter5(Origen,LugaresDestino,L1)).

/*------------------------------------------------Gramatica Libre de Contexto-------------------------------------------*/

/*Pregunta 1*/
% Valida si la respuesta de la pregunta 1 tiene una gramatica valida en
% nuestro lenguaje natural. La respuesta 1 debe estar compuesta pero no
% limitada a un saludo, un pronombre, un verbo y un lugar.
q1(O,X):-nominal1(Sustan),verbal1(Verbo,X),append(Sustan,Verbo,O),!.
% Puede solo contener el lugar donde se encuentra.
q1(X,X):-lugar(X),!.
% O tambien por un verbo gerundio en presente.
q1(O,X):-verbal1(O,X),!.
nominal1(Sustan):- saludos(S),nombre(N),append(S,N,Sustan).
nominal1(Sustan):-saludos(Sustan).
nominal1(Sustan):- nombre(Sustan).
verbal1(Verbo,X):-verbos(V),lugar(X),append(V,X,Verbo).
verbal1(Verbo,X):-prepo(P),lugar(X), append(P,X,Verbo).
verbos(S):-verbo(V),prepo(P),append(V,P,S).
verbos(S):-verbom(V),prepo(P),append(V,P,S).
verbom(S):-me(M),verbo3(V),append(M,V;S).
saludos(S):-saludo(T),waze(F),append(T,F,S).
me(['me']).

/*pregunta 2*/
% Valida si la respuesta de la pregunta 2 tiene una gramatica valdia en
% nuestro lenguaje natural. La respuesta 2 debe estar compuesta pero no
% limitada a una preposicion y un lugar.
q2(X,X):-lugar(X),!.
q2(O,X):-verbal2(O,X),!.
verbal2(Verbo,X):-prepo(P),lugar(X),append(P,X,Verbo).

/*pregunta 3*/
% Valida si la respuesta de la pregunta 3 tiene una gramatica valida en
% nuestro lenguaje natural. La respuesta 3 debe estar compuesta pero no
% limitada a un verbo indicativo en presente, un verbo regular, una
% preposicion y un lugar que se quiera visitar.
q3(O,X):-verbal3(V),conec(C,X),append(V,C,O),!.
q3(O,X):-conec(O,X),!.
q3(X,X):-local(X),!.
no([no]).
verbal3(V):-verbo2(B),extra(P),append(B,P,V).
extra(P):-que(C),verbor(X),append(C,X,P).
extra(P):-verbor(P).
conec(C,Y):-prepo2(P),local(Y),append(P,Y,C).
conec(C,Y):-prepo3(P),prepo4(Q),local2(Y),append(P,Q,C1),append(C1,Y,C).
que(['que']).

/*pregunta 4 */
% Valida que un local que se desea visitar se encuentra en los hechos y
% lo retorna de lo contrario, de lo contrario retorna falso.
% Puede aceptar una oracion compuesta con 'me gustaria' ademas del
% nombre del local.
q4(O,X):-nose(N),nLocal(X),append(N,X,O),!.
% O solo el nombre del local.
q4(X,X):-nLocal(X),!.
nose(N):-me(M),verbo4(V),append(M,V,N),!.

/*pregunta 5 */
% Valida si la respuesta de la pregunta 5 tiene una gramatica valida en
% nuestro lenguaje natural. La respuesta 5 debe estar compuesta pero no
% limitada a se, un verbo indicativo de lugar en presente, una
% preposicion y un lugar.
q5(O,X):-verbal5(V),extra5(C,X),append(V,C,O),!.
% O solamente a una preposicion y un lugar.
q5(O,X):-extra5(O,X),!.
verbal5(B):-se(S),verboL(V),append(S,V,B).
extra5(X,Y):-prepo(P),lugar(Y),append(P,Y,X).
se(['se']).

/*Verbos*/
% Verbos admitidos en la gramatica.
verbo3(['localizo']).
verbo3(['ubico']).
verbo3(['encuentro']).
verbo4(['gustaria']).
verboL(['localiza']).
verboL(['ubica']).
verboL(['encuentra']).
verboL(['establece']).
verbor(['pasar']).
verbor(['viajar']).
verbor(['ir']).
verbor(['comprar']).
verbor(['desviar']).
verbor(['vender']).
verbor(['comer']).
verbo(['estoy']).
verbo(['ando']).
verbo(['voy']).
verbo(['habito']).
verbo2(['tengo']).
verbo2(['deseo']).
verbo2(['quiero']).
verbo2(['tendria']).

/*Saludos*/
% Saludos admitidos en la gramatica.
saludo(['hola']).
saludo(['buenas']).

/*Nombres*/
% Nombres admitidos en la gramatica.
nombre(['yo']).
waze(['drwazelog']).

/*Preposiciones*/
% Preposiciones admitidas en la gramatica.
prepo(['en']).
prepo(['por']).
prepo(['para']).
prepo2(['al']).
prepo3(['a']).
prepo4(['la']).

/*Locales*/
% Locales admitidos en la gramatica.
local(['super']).
local(['restaurante']).
local(['supermercado']).
local(['colegio']).
local(['hospital']).
local(['instituto']).
local(['tec']).
local(['salon']).
local(['parque']).
local(['hotel']).
local(['mall']).
local(['museo']).
local(['estadio']).
local(['aeropuerto']).
local(['teatro']).
local(['cine']).
local2(['ucr']).
local2(['una']).
local2(['facultad']).
local2(['ferreteria']).
local2(['panaderia']).

/*Nombres de locales permitidos*/
% Nombres de locales admitidos en la gramatica.
nLocal(['morazan']).
nLocal(['lasabana']).
nLocal(['central']).
nLocal(['automercado']).
nLocal(['walmart']).
nLocal(['masxmenos']).
nLocal(['sabores']).
nLocal(['novilloalegre']).
nLocal(['cientifico']).
nLocal(['tecnico']).
nLocal(['bilingue']).
nLocal(['mexico']).
nLocal(['ni√±os']).
nLocal(['jimenez']).
nLocal(['cosvic']).
nLocal(['multiplaza']).
nLocal(['metropoli']).
nLocal(['nacional']).
nLocal(['jade']).
nLocal(['municipal']).
nLocal(['arte']).
nLocal(['saprissa']).
nLocal(['morerasoto']).
nLocal(['fellomeza']).
nLocal(['rosabal']).
nLocal(['melicosalazar']).
nLocal(['cinemark']).
nLocal(['cinepolis']).
nLocal(['sedecentral']).
nLocal(['barrioamon']).
nLocal(['santaclara']).
nLocal(['danieloduber']).
nLocal(['juansantamaria']).
nLocal(['medicina']).
nLocal(['ingenieria']).
nLocal(['brenes']).
nLocal(['musmani']).
nLocal(['ppk']).
nLocal(['rodrigofacio']).
nLocal(['sedeguanacaste']).
nLocal(['omardengo']).
nLocal(['chorotega']).


/*------------------------------------------------Gramatica Libre de Contexto-------------------------------------------*/

/************************************************GRAFO***********************************************************/
connected(X,Y,L) :- edge(X,Y,L) ; edge(Y,X,L).

path(A,B,Path,Len) :-
       travel(A,B,[A],Q,Len),
       reverse(Q,Path).

travel(A,B,P,[B|P],L) :-
       connected(A,B,L).
travel(A,B,Visited,Path,L) :-
       connected(A,C,D),
       C \== B,
       \+member(C,Visited),
       travel(C,B,[C|Visited],Path,L1),
       L is D+L1.

shortest(A,B,Path,Length) :-
   setof([P,L],path(A,B,P,L),Set),
   Set = [_|_], % fail if empty
   minimal(Set,[Path,Length]).

minimal([F|R],M) :- min(R,F,M).

% minimal path
min([],M,M).
min([[P,L]|R],[_,M],Min) :- L < M, !, min(R,[P,L],Min).
min([_|R],M,Min) :- min(R,M,Min).

append_1([],X,X).
append_1([X|W],Z,[X|C]):- append(W,Z,C).

get_whole_path([],_,[],0).
get_whole_path([X |Destinos],Origen,Camino,Largo):-
       shortest(Origen,X,Camino_2,Largo_2),
      append_1(Camino_2,Camino_fin,Camino),
       get_whole_path(Destinos,X,Camino_fin,Largo_final),Largo is Largo_final + Largo_2.

delete_one(_, [], []).
delete_one(Term, [Term|Tail], Tail).
delete_one(Term, [Head|Tail], [Head|Result]) :-
  delete_one(Term, Tail, Result),!.


delete_all([],X,X).
delete_all([X|Z],Lista,Resultado):-
       delete_one(X,Lista,Result),delete_all(Z,Result,Resultado).
