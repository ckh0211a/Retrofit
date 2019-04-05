var express = require('express');
var db = require('../db');
var router = express.Router();

router.post('/create', function(req, res, next) {

    var menu = req.body.menu;
    var category = req.body.category;
    var restaurant = req.body.restaurant;

    var sql = "SELECT * FROM menu WHERE menu=?";
    var input = [menu];

    db.get().query(sql, input, function(err, result){
        if(err){
            res.json({message:"DB error", code:403, result:false});
        }
        if(result.length > 0){
            res.json({message:"이미 데이타가 있습니다.", result:false, code:401});
        }else{
            var sql_insert = "INSERT INTO menu(menu, category, restaurant) VALUES(?,?,?)";
            var input = [menu, category, restaurant];

            db.get().query(sql_insert, input, function(err, result){
                if(err){
                    res.json({message:"오류",code:401, result:false});
                }else {
                    res.json({message:"입력이 되었습니다.", code:200, result:true});
                }
            });
        }
    });
});

router.get('/read', function (req, res, next) {

    var menu = req.query.menu;
    var sql = "SELECT * FROM menu WHERE menu=?";
    var input = [menu];

    db.get().query(sql, input, function (err, result) {
        if (err) {
            res.json({message: "ERROR", result: "OK", code: 401});
        } else {
            if (result.length > 0) {
                res.json({data: result[0], result: "OK", code: 200, message: "조회되었습니다."});
            }
        }
    });
});


router.put('/update', function(req, res, next) {

    var menu = req.body.menu;
    var category = req.body.category;
    var restaurant = req.body.restaurant;

    var sql = "UPDATE menu SET category=?, restaurant=?  WHERE menu=?";
    var input = [category, restaurant, menu];

    db.get().query(sql, input, function(err,result){

        if(err) {
            res.json({code:401, message:"DB error", result:"Failure"});
        }else {
            res.json({code:200, message:"수정되었습니다.", result:"OK"});
        }
    });
});


router.delete('/delete', function (req, res, next) {

    var menu = req.query.menu;
    var sql = "DELETE FROM menu WHERE menu=?";
    var input = [menu];

    db.get().query(sql, input, function (err, result) {

        if (err) {
            res.json({message: "ERROR", result: "OK", code: 401});
        } else {
            if (result.affectedRows > 0) {
                res.json({result: "OK", code: 200, message: "삭제되었습니다."});
            }
        }
    });
});

module.exports = router;
