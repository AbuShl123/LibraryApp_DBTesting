select count(*) from users;

select count(*) from books;

select count(*) from book_borrow
where is_returned=0;

select name from book_categories;

select b.name, b.year, b.author, b.description, b.isbn, c.name
from books b join book_categories c
on b.book_category_id = c.id
where b.name='Agile Testing';

select full_name from users
where email='librarian55@library';