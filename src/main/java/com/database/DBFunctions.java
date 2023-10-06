package com.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Course;
import com.entity.CourseRegister;
import com.entity.User;

public class DBFunctions {
	public List<User> getUsersFromDatabase() {
		List<User> users = new ArrayList<>();
		DBmanager dbMgr = new DBmanager();
		Connection conn = null;

		try {
			conn = dbMgr.getConnection();
			if (conn != null) {
				String query = "select * from TrainingDB.users";
				PreparedStatement prepare_statement = conn.prepareStatement(query);
				ResultSet resultSet = prepare_statement.executeQuery();
				while (resultSet.next()) {
					User user = new User();
					user.setId(resultSet.getInt("id"));
					user.setUsername(resultSet.getString("name"));
					user.setEmail(resultSet.getString("email"));
					user.setAdmin(resultSet.getBoolean("is_admin"));
					users.add(user);
				}

				resultSet.close();
				prepare_statement.close();
			} else {
				return null;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return users;
	}

	public List<CourseRegister> getRegistrationRecordPerEmail(String email) {
		List<CourseRegister> courses_reg = new ArrayList<>();
		DBmanager dbMgr = new DBmanager();
		Connection conn = null;

		try {
			conn = dbMgr.getConnection();
			if (conn != null) {
				String query = "SELECT u.name AS user_name, u.email AS user_email, urc.course_id, c.course_name, c.price AS course_price "
						+ "FROM user_register_course urc " + "JOIN users u ON urc.user_id = u.id "
						+ "JOIN courses c ON urc.course_id = c.id " + "WHERE u.email=?" + "ORDER BY urc.id DESC "
						+ "LIMIT 1;";
				PreparedStatement prepare_statement = conn.prepareStatement(query);
				prepare_statement.setString(1, email);
				ResultSet resultSet = prepare_statement.executeQuery();
				while (resultSet.next()) {
					CourseRegister course_reg = new CourseRegister();
					course_reg.setUsername(resultSet.getString("user_name"));
					course_reg.setEmail(resultSet.getString("user_email"));
					course_reg.setCourse_id(resultSet.getInt("course_id"));
					course_reg.setCourse_name(resultSet.getString("course_name"));
					course_reg.setCourse_price(resultSet.getString("course_price"));
					courses_reg.add(course_reg);
				}

				resultSet.close();
				prepare_statement.close();
			} else {
				return null;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return courses_reg;
	}

	public List<CourseRegister> getRegistrationRecord() {
		List<CourseRegister> courses_reg = new ArrayList<>();
		DBmanager dbMgr = new DBmanager();
		Connection conn = null;

		try {
			conn = dbMgr.getConnection();
			if (conn != null) {
				String query = "SELECT u.name AS user_name, u.email AS user_email, urc.course_id, c.course_name, c.price AS course_price "
						+ "FROM user_register_course urc " + "JOIN users u ON urc.user_id = u.id "
						+ "JOIN courses c ON urc.course_id = c.id ";
				PreparedStatement prepare_statement = conn.prepareStatement(query);
				ResultSet resultSet = prepare_statement.executeQuery();
				while (resultSet.next()) {
					CourseRegister course_reg = new CourseRegister();
					course_reg.setUsername(resultSet.getString("user_name"));
					course_reg.setEmail(resultSet.getString("user_email"));
					course_reg.setCourse_id(resultSet.getInt("course_id"));
					course_reg.setCourse_name(resultSet.getString("course_name"));
					course_reg.setCourse_price(resultSet.getString("course_price"));
					courses_reg.add(course_reg);
				}

				resultSet.close();
				prepare_statement.close();
			} else {
				return null;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return courses_reg;
	}

	public List<Course> getCoursesFromDatabase() {
		List<Course> courses = new ArrayList<>();
		DBmanager dbMgr = new DBmanager();
		Connection conn = null;

		try {
			conn = dbMgr.getConnection();
			if (conn != null) {
				String query = "select * from TrainingDB.courses";
				PreparedStatement prepare_statement = conn.prepareStatement(query);
				ResultSet resultSet = prepare_statement.executeQuery();
				while (resultSet.next()) {
					Course course = new Course();
					course.setId(resultSet.getInt("id"));
					course.setName(resultSet.getString("course_name"));
					course.setPrice(resultSet.getString("price"));
					courses.add(course);
				}

				resultSet.close();
				prepare_statement.close();
			} else {
				return null;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return courses;
	}

	public boolean isCourseExists(String courseName) {
		boolean exists = false;
		DBmanager dbMgr = new DBmanager();
		Connection conn = null;

		try {
			conn = dbMgr.getConnection();
			String sql = "SELECT COUNT(*) FROM courses WHERE course_name = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, courseName);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int count = resultSet.getInt(1);
				exists = (count > 0); // If count is greater than 0, the course exists
			}
			resultSet.close();
			conn.close();
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}

		return exists;
	}
	public boolean isUserExists(String email) {
		boolean exists = false;
		DBmanager dbMgr = new DBmanager();
		Connection conn = null;

		try {
			conn = dbMgr.getConnection();
			String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int count = resultSet.getInt(1);
				exists = (count > 0); // If count is greater than 0, the user exists
			}
			resultSet.close();
			conn.close();
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}

		return exists;
	}

	public boolean insertCourses(String course_name, String course_price)
			throws java.sql.SQLIntegrityConstraintViolationException {
		try (Connection conn = new DBmanager().getConnection()) {
			if (conn != null) {
				String query = "INSERT INTO TrainingDB.courses (course_name, price) VALUES (?, ?)";
				try (PreparedStatement prepare_statement = conn.prepareStatement(query)) {
					prepare_statement.setString(1, course_name);
					prepare_statement.setString(2, course_price);
					int result = prepare_statement.executeUpdate();
					return result == 1;
				}
			}
		} catch (ClassNotFoundException | SQLException | IOException e1) {
			e1.printStackTrace();
		}
		return false;
	}

	public boolean deleteCourse(int courseId) {
		try (Connection conn = new DBmanager().getConnection()) {
			if (conn != null) {
				String query = "DELETE FROM TrainingDB.courses WHERE id = ?";
				try (PreparedStatement prepare_statement = conn.prepareStatement(query)) {
					prepare_statement.setInt(1, courseId);
					int result = prepare_statement.executeUpdate();
					return result == 1;
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return false;
	}

	public boolean deleteUser(int userId) {
		try (Connection conn = new DBmanager().getConnection()) {
			if (conn != null) {
				String query = "DELETE FROM TrainingDB.users WHERE id = ?";
				try (PreparedStatement prepare_statement = conn.prepareStatement(query)) {
					prepare_statement.setInt(1, userId);
					int result = prepare_statement.executeUpdate();
					return result == 1;
				}
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateUser(User user) {
		try (Connection conn = new DBmanager().getConnection()) {
			if (conn != null) {
				String query = "UPDATE TrainingDB.users SET name=?,  email=?, password=?, is_admin=? WHERE id=?";
				PreparedStatement preparedStatement = conn.prepareStatement(query);
				preparedStatement.setString(1,user.getUsername());
				preparedStatement.setString(2,user.getEmail());
				preparedStatement.setString(3,user.getPassword());
				preparedStatement.setBoolean(4,user.isAdmin());
				preparedStatement.setInt(5,user.getId());
				int rowsAffected = preparedStatement.executeUpdate();
				return rowsAffected == 1;
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public User getUser(int userId) {
		User user = new User();
		try (Connection conn = new DBmanager().getConnection()) {
			if (conn != null) {
				String query = "SELECT * FROM TrainingDB.users WHERE id = ?";
				try (PreparedStatement prepare_statement = conn.prepareStatement(query)) {
					prepare_statement.setInt(1, userId);
					ResultSet resultSet = prepare_statement.executeQuery();
					if (resultSet.next()) {
						user.setId(resultSet.getInt("id"));
						user.setUsername(resultSet.getString("name"));
						user.setEmail(resultSet.getString("email"));
						user.setAdmin(resultSet.getBoolean("is_admin"));
						user.setPassword(resultSet.getString("password"));
					}
					resultSet.close();
					conn.close();
					return user;
				}
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean updateCourse(int courseId, String courseNewName, String courseNewPrice) {
		try (Connection conn = new DBmanager().getConnection()) {
			if (conn != null) {
				String query = "UPDATE TrainingDB.courses SET course_name=?, price=? WHERE id=?";
				try (PreparedStatement prepare_statement = conn.prepareStatement(query)) {
					prepare_statement.setString(1, courseNewName);
					prepare_statement.setString(2, courseNewPrice);
					prepare_statement.setInt(3, courseId);
					int result = prepare_statement.executeUpdate();
					return result == 1;
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return false;
	}
}
