package br.com.fiap.techFlix.adapter.persistence.bookmarkvideo;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkVideoRepository extends MongoRepository<BookmarkVideoDocument, String> {

    Optional<BookmarkVideoDocument> findByVideoIdAndUserId(String videoId, String userId);

    boolean existsByUser_IdAndVideo_Id(String userId, String videoId);

    @Aggregation(pipeline = {
            "{ $match: { 'user._id': ?0 } }",
            "{ $unwind: '$video.categories' }",
            "{ $group: { _id: '$video.categories.name', name: { $first: '$video.categories.name' }, count: { $sum: 1 } } }",
            "{ $project: { _id: 0, name: 1, count: 1 } }",
            "{ $sort: { count: -1 } }",
            "{ $limit: 5 }"
    })
    List<UserBookmarkedCategories> getTop5LikedCategories(String userId);
}
