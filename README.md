# Aplikacje Internetowe
# Dokumentacja projektu „Serwis StackOverflow”
# Grzegorz Rachel - L4

## 1. Harmonogram prac
![Harmonogram 1](https://s9.postimg.org/5cs1zuc7z/1.png)
![Harmonogram 2](https://s9.postimg.org/9zy41lzkv/2.png)
![Harmonogram 3](https://s9.postimg.org/52kjghxlr/image.png)

## 2. Założenia funkcjonalne

Użytkownik może:
* zarejestrować się w systemie,
* zalogować się w systemie,
* po zalogowaniu:
  * zadawać pytania,
  * edytować i usuwać swoje pytania,
  * udzielać odpowiedzi na pytania innych użytkowników,
  * oznaczać posty innych użytkowników jako wymagające moderacji,
  * po osiągnieciu odpowiedniej ilości punktów reputacji:
    * oceniać posty innych użytkowników,
    * dodawać komentarze.

Moderator:
* otrzymuje uprawnienia od Administratora,
* posiada możliwości takie jak użytkownik oraz może:
  * usuwać posty innych użytkowników,
  * edytować posty innych użytkowników,
  * zamykać tematy,
  * oznaczać tematy jako duplikaty.
 

## 3. Założenia niefunkcjonalne

Do wykonania aplikacji zostaną użyte:
* git,
* github.com – https://github.com/grachel/stack,
* heroku.com – https://stack-gr.herokuapp.com,
* PostgreSQL,
* Spring Framework,
* Hibernate,
* Thymeleaf.

## 4. Diagram ERD
![Diagram ERD](https://s15.postimg.org/sbh3c69uj/ERD.png)

## 5. Diagram UML
[![diagram.png](https://s16.postimg.org/gz8g2klc5/diagram.png)](https://postimg.org/image/gz8g2klc1/)

## 5. Opis elementu

Do przechowywania informacji o 'Postach' została utworzona tabela w bazie danych o nazwie 'posts'.
```sql
CREATE TABLE posts (
    id integer NOT NULL,
    posttype text NOT NULL,
    acceptedanswerid integer,
    parentid integer,
    creationdate timestamp without time zone DEFAULT now(),
    score integer,
    body text NOT NULL,
    owneruserid integer,
    lasteditoruserid integer,
    lasteditdate timestamp without time zone,
    title text,
    tags text,
    closeddate timestamp without time zone,
    closedreason text
)
```
Post może być pytaniem lub odpowiedzią. Informacja o typie przechowywana jest w kolumnie 'posttype'.
Tabela jest ona połączona ze sama sobą poprzez kolumnę parentid, w której znajduje się id postu rodzica (jeśli ten post jest odpowiedzią). Poprzez kolumnę 'owneruserid' połączona jest z tabela 'users' w której przechowywane są informacje o użytkownikach.  

W aplikacji została utworzona klasa PostsEntity, która łączy obiekt Javy (za pomocą Hibernate oraz javax.persistence) z danymi z bazy danych. Poszczególne kolumny połączone są z właściwościami klasy za pomocą adnotacji. Na przykład:
```java
    @OneToMany(mappedBy = "postsByPostid", fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @OrderBy("id")
    public Collection<CommentariesEntity> getCommentariesById() {
        return commentariesById;
    }

    public void setCommentariesById(Collection<CommentariesEntity> commentariesById) {
        this.commentariesById = commentariesById;
    }
```    

OneToMany oznacza połącznie jeden do wielu, poprzez kolumnę postid w tabeli 'commentaries'. 
OrderBy oznacza ze kolekcja zawsze powinna być posortowana wg. wartości w kolumnie 'id'.

W celu uproszczenia komunikacji z baza danych, utworzona zostały wrapper w postaci klasy Post. Znajdują się tam metody ułatwiające np. dodawanie i pobieranie komentarzy czy dodawanie punktów.

W klasie PostsController, dzięki adnotacji @RequestMapping(value = "/post"), przechwytywane są żądania wysłane pod adres zaczynający się od '/post'. 
Aby utworzyć pytanie należy wysłać zapytanie pod adres '/post/ask' metoda POST. Przekazanymi parametrami musza być 'title' zawierający tytuł postu oraz 'body' zawierający treść. Korzystając z tego samego adresu ale używając metody GET, można pobrać formularz do tworzenia pytania.  
```java
    @RequestMapping(value = "ask", method = RequestMethod.POST)
    public ModelAndView ask(@RequestParam(value = "title") String title,
                                     @RequestParam(value = "body") String body) {

        Post post = new Post(User.getCurrentUser());
        post.setTitle(title);
        post.setBody(body);
        post.setType("Q");
        post.setCreationDate(new Timestamp(System.currentTimeMillis()));
        post.save();

        return new ModelAndView("redirect:/post/my");
    }
```
W metodzie ask, dla zadania POST, tworzymy nowy Post w którym zostaną zapisane tytuł i treść pobrana z parametrów. Post będzie przypisany do aktualnie zalogowanego użytkownika. Po zapisie, użytkownik zostanie przekierowany na stronę '/post/my' gdzie wyświetlane są jego wszystkie posty.

Widok renderowany jest za pomocą Thymeleafa. 
W pliku resources\templates\home\layout.html znajduje się ogólny layout aplikacji.
Znacznik:
```html
<div layout:fragment="content">Fake content</div>
```
oznaczony został jako fragment, tzn. ze przy leczeniu layoutu z zawartością, ten znacznik zostanie zastąpiony.
W pliku resources\templates\post\ask.html znajduje się template który powinien zostać użyty przy tworzeniu widoku dla formularza dodawania postów. 
```html
<div layout:fragment="content" class="content">
    <form class="form-narrow form-horizontal" method="post" th:action="@{ask}">
        <fieldset>
            <legend>Enter new question</legend>
            <div class="form-group">
                <label for="title" class="col-lg-2 control-label">Title</label>
                <div class="col-lg-10">
                    <input type="text" class="form-control" id="title" placeholder="Title" name="title"/>
                </div>
            </div>
            <div class="form-group">
                <label for="body" class="col-lg-2 control-label">Description</label>
                <div class="col-lg-10">
                    <textarea class="form-control" rows="10" id="body" name="body" required></textarea>
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-offset-2 col-lg-10">
                    <button type="submit" class="btn btn-default">Submit!</button>
                </div>
            </div>
        </fieldset>
    </form>
</div>
```
Znajduje się w nim fragment który wypełni layout, tworząc gotowy widok.
W znaczniku ‘form’ zdefiniowana została wartość "@{ask}" dla parametru th:action oraz "post" dla parametru method. Oznacza to ze po naciśnięciu przycisku Submit, wykonane zostanie żądanie POST pod względny adres ‘ask’. Czyli to żądanie zostanie obsłużone przez metodę z kontrolera PostsController i post powinien zostać zapisany. 

