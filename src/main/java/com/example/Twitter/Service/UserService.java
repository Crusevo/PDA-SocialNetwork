package com.example.Twitter.Service;

import com.example.Twitter.Model.Interests;
import com.example.Twitter.Model.Post;
import com.example.Twitter.Model.User;
import com.example.Twitter.Repository.InterestsRepository;
import com.example.Twitter.Repository.PostRepository;
import com.example.Twitter.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    @Autowired UserRepository userRepository;
    @Autowired PostRepository postRepository;
    @Autowired InterestsRepository interestsRepository;
    @Autowired BCryptPasswordEncoder bCryptPasswordEncoder;



    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User addUser(User user) {

        List<User> userNameFromDatabase = userRepository.findByUserName(user.getUserName());
        List<User> userLastNameFromDatabase = userRepository.findByLastName(user.getLastName());

        if(userNameFromDatabase.isEmpty() || userLastNameFromDatabase.isEmpty()){

            if(user.getUserName() != null && user.getLastName() != null && user.getPassword() != null){

                String encodePassword = bCryptPasswordEncoder.encode(user.getPassword());

                user.setPassword(encodePassword);

                userRepository.save(user);

            }else {

                System.out.println("Proszę podać imię, nazwisko oraz hasło");
            }


        } else {
            System.out.println("Uźytkownik jest już w bazie danych");
        }

        return user;
    }

    public void deleteUser(long id){
        if(userRepository.findById(id).isPresent()){
            User byId = userRepository.findById(id).orElseThrow();
            byId.getSharedPostList().clear();
            byId.getComments().clear();

            userRepository.deleteById(id);
            System.out.println("Usunięto użytkownika o id: " + id);
        }else{
            System.out.println("Nie znaleziono użytkownika o podanym id: " + id);
        }

    }

    public User userAddPost (Post post, long id){

        if(userRepository.findById(id).isPresent()) {

            User userFromDataBase = userRepository.findById(id).orElseThrow();

            List<Post> userFromDataBasePosts = userFromDataBase.getPosts();

            if (post.getPostTitle() != null && post.getPostContent() != null) {

                post.setUser(userFromDataBase);

                postRepository.save(post);

                userFromDataBasePosts.add(post);

                userRepository.save(userFromDataBase);

            } else {

                System.out.println("Proszę podać tutuł oraz treść posta");


            }

            return userFromDataBase;

        }else{
            System.out.println("Użytkownik o id " + id + " nie istnieje");
        }

        return null;

    }


    public void addProfileImage(long id, String image) {

        User byId = userRepository.findById(id).orElseThrow();
        byId.setImage(image);
        userRepository.save(byId);
    }

    public User sharePost(long userId, long postId){

        Post post = postRepository.findById(postId).orElseThrow();

        User userById = userRepository.findById(userId).orElseThrow();

        List<Post> sharedPostList = userById.getSharedPostList();

        sharedPostList.add(post);

        userRepository.save(userById);

        return userById;

    }

    public User addToFriend(long userWchichAddToFavoriteId, long userId) {

        User userToFriend = userRepository.findById(userId).orElseThrow();

        User userWchichAddToFavorite = userRepository.findById(userWchichAddToFavoriteId).orElseThrow();

        List<User> favoriteUsersList = userWchichAddToFavorite.getFavoriteUsers();

        if (!favoriteUsersList.contains(userToFriend) && userWchichAddToFavoriteId != userId){

            favoriteUsersList.add(userToFriend);

            userRepository.save(userWchichAddToFavorite);

        } else {

            System.out.println("Dodałeś już użytkownika do znajomych");

        }

        return userWchichAddToFavorite;

    }

    public List<User> findByAdress(String address) {

        List<User> byAddressList = userRepository.findByAddress(address);

        if(byAddressList.isEmpty()){
            System.out.println("Brak użytkowników dla podanego adresu");
        }

        return byAddressList;
    }

    public User addInterests(Interests interests, long userId) {

        User userFromDB = userRepository.findById(userId).orElseThrow();

        List<Interests> userFromDBInterests = userFromDB.getInterestsList();

        List<Interests> byInterestsName = interestsRepository.findByInterestsName(interests.getInterestsName());

        if(userFromDBInterests.contains(interests)){
            System.out.println("Zainteresowanie znajduje się już na liście");

        } else if (!userFromDBInterests.contains(interests) && !byInterestsName.contains(interests)) {

            interestsRepository.save(interests);
            userFromDBInterests.add(interests);
            userRepository.save(userFromDB);

        }else if(!userFromDBInterests.contains(interests) && byInterestsName.contains(interests)){

            interests.setInterestsName(byInterestsName.get(0).getInterestsName());
            interests.setInterestsId(byInterestsName.get(0).getInterestsId());

            userFromDBInterests.add(interests);
            userRepository.save(userFromDB);

        }

        return userFromDB;
    }
}
