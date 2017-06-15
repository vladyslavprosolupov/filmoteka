package web.services;

import web.dao.FilmDb;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Rostyk on 12.06.2017.
 */
@Service("FilmService")
@Transactional
public class FilmService {
    @Autowired(required = true)
    private SessionFactory sessionFactory;

    public List<FilmDb> getAllFilms(){
        Session session = sessionFactory.getCurrentSession();
        List<FilmDb> listOfFilms = session.createQuery("FROM FilmDb").list();
        return listOfFilms;
    }

    public FilmDb getFilmWithId(String id){
        Session session = sessionFactory.getCurrentSession();
        return (FilmDb) session.createQuery("from FilmDb f where f.id=" + id).list().get(0);
    }

    public void saveOrUpdate(FilmDb filmToSave){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(filmToSave);
    }
}
