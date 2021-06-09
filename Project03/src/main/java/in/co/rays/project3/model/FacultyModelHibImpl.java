package in.co.rays.project3.model;



import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project3.dto.CollegeDTO;
import in.co.rays.project3.dto.CourseDTO;
import in.co.rays.project3.dto.FacultyDTO;
import in.co.rays.project3.dto.SubjectDTO;
import in.co.rays.project3.exception.ApplicationException;
import in.co.rays.project3.exception.DuplicateRecordException;
import in.co.rays.project3.util.HibDataSource;


/**
 * Hibernate Implementation of FacultyModel
 *
 * @author Vaishali
  */

public class FacultyModelHibImpl implements FacultyModelInt {
	
//	private static final String CollegeName = null;

	public long add(FacultyDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		System.out.println("faculty>>>>>>>>>>>>>"+dto.getFirst_Name());
		System.out.println("faculty>>>>>>>>>>>>>"+dto.getCollegeName());
		System.out.println("faculty>>>>>>>>>>>>>"+dto.getCollege_id());
		System.out.println(dto.getGender());
		System.out.println(dto.getLast_Name());
		
		
		Session session = null;
		Transaction tx = null;
		long pk = 0L;
	
		//get CollegeName
		CollegeModelInt cModel = ModelFactory.getInstance().getCollegeModel();
        CollegeDTO collegeDTO = cModel.findByPK(dto.getCollege_id());
        dto.setCollegeName(collegeDTO.getCollegeName());
        
      //  get CourseName
        CourseModelInt cm =ModelFactory.getInstance().getCourseModel();
        CourseDTO cd =cm.findByPK(dto.getCourse_id());
        dto.setCourse_Name(cd.getCourse_Name());
        
        //gets subjcetName
        
        SubjectModelInt smodel =ModelFactory.getInstance().getSubjectModel();
        SubjectDTO sd =smodel.findByPK(dto.getSubject_id());
        dto.setSubject_Name(sd.getSubject_Name());
		


		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.save(dto);
			pk = dto.getId();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();

			}
			//throw new ApplicationException("Exception in faculty Add " + e.getMessage());
		} finally {
			session.close();
		}
		return pk;
	}

	public void delete(FacultyDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.delete(dto);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();

			}
			throw new ApplicationException("Exception in faculty delete " + e.getMessage());
		} finally {
			session.close();
		}
	}

	public void update(FacultyDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		
		CollegeModelInt col = ModelFactory.getInstance().getCollegeModel();
		CollegeDTO coldto = col.findByPK(dto.getCollege_id());
		dto.setCollegeName(coldto.getCollegeName());
		
		
		CourseModelInt cor = ModelFactory.getInstance().getCourseModel();
		CourseDTO coudto = cor.findByPK(dto.getCourse_id());
		dto.setCourse_Name(coudto.getCourse_Name());
		
		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.update(dto);
			tx.commit();

		} catch (HibernateException e) {
			e.printStackTrace();
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();

			}
			throw new ApplicationException("Exception in faculty update " + e.getMessage());
		} finally {
			session.close();
		}
	}

	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

	public List list(int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(FacultyDTO.class);
			if (pageSize > 0) {
				pageNo = ((pageNo - 1) * pageSize) + 1;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = criteria.list();
		} catch (HibernateException e) {

			throw new ApplicationException("Exception : Exception in  faculty list");
		} finally {
			session.close();
		}
		return list;
	}

	public List search(FacultyDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	public List search(FacultyDTO dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub

		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(FacultyDTO.class);
			
				if (dto.getId() > 0) {
					criteria.add(Restrictions.eq("id", dto.getId()));
				}
				if (dto.getFirst_Name() != null && dto.getFirst_Name().length() > 0) {
					criteria.add(Restrictions.like("First_Name", dto.getFirst_Name() + "%"));
				}
				if (dto.getEmail_id() != null && dto.getEmail_id().length() > 0) {
					criteria.add(Restrictions.like("Email_id", dto.getEmail_id() + "%"));
				}
				if (dto.getLast_Name() != null && dto.getLast_Name().length() > 0) {
					criteria.add(Restrictions.like("Last_Name", dto.getLast_Name() + "%"));
				}
				if (dto.getCollege_id() > 0) {
					criteria.add(Restrictions.eq("College_id", dto.getCollege_id()));
				}
				if (dto.getCourse_id() > 0) {
					criteria.add(Restrictions.eq("Course_id", dto.getCourse_id()));
				}
				if (dto.getSubject_id() > 0) {
					criteria.add(Restrictions.eq("Subject_id", dto.getSubject_id()));
				}
			

			// if page size is greater than zero the apply pagination
			if (pageSize > 0) {
				criteria.setFirstResult(((pageNo - 1) * pageSize));
				criteria.setMaxResults(pageSize);
			}

			list = criteria.list();
		} catch (HibernateException e) {

			throw new ApplicationException("Exception in course search");
		} finally {
			session.close();
		}

		return list;
	}

	public FacultyDTO findByPK(long pk) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		FacultyDTO dto = null;
		try {
			session = HibDataSource.getSession();

			dto = (FacultyDTO) session.get(FacultyDTO.class, pk);
		} catch (HibernateException e) {

			throw new ApplicationException("Exception : Exception in getting faculty by pk");
		} finally {
			session.close();
		}
		return dto;
	}

	public FacultyDTO findByEmailId(String emailId) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		FacultyDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(FacultyDTO.class);
			criteria.add(Restrictions.eq("emailId", emailId));
			List list = criteria.list();
			if (list.size() > 0) {
				dto = (FacultyDTO) list.get(0);
			}
		} catch (HibernateException e) {

			//throw new ApplicationException("Exception in getting faculty by Login " + e.getMessage());

		} finally {
			session.close();
		}
		return dto;
	}
}
