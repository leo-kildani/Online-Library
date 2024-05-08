//for Favorite Authors tab
$(document).ready(function(){
    $('a[data-bs-toggle="tab"]').on('shown.bs.tab', function (e) {
        var target = $(e.target).attr("href"); // activated tab
        if(target === "#authors") {
            $.ajax({
                url: "/userprofile/favoriteAuthors",
                success: function(result) {
                    $("#authors").html(result);
                }
            });
        }
    });
});

//for Liked Genres tab
$(document).ready(function(){
    $('a[data-bs-toggle="tab"]').on('shown.bs.tab', function (e) {
        var target = $(e.target).attr("href"); // activated tab
        if(target === "#genres") {
            $.ajax({
                url: "/userprofile/likedGenres",
                success: function(result) {
                    $("#genres").html(result);
                }
            });
        }
    });
});

//for borrowed books tab
$(document).ready(function(){
    $('a[data-bs-toggle="tab"]').on('shown.bs.tab', function (e) {
        var target = $(e.target).attr("href"); // activated tab
        if(target === "#books") {
            $.ajax({
                url: "/userprofile/borrowedBooks",
                success: function(result) {
                    $("#books").html(result);
                }
            });
        }
    });
});

//for recommendations tab
$(document).ready(function(){
    $('a[data-bs-toggle="tab"]').on('shown.bs.tab', function (e) {
        var target = $(e.target).attr("href"); // activated tab
        if(target === "#recs") {
            $.ajax({
                url: "/userprofile/recommendations",
                success: function(result) {
                    $("#recs").html(result);
                }
            });
        }
    });
});