var express=require('express');
var fs = require("fs");
var ex=express();
ex.use('/',express.static('wy'));
ex.use('/node_modules',express.static('node_modules'));
ex.use('/c',express.static('choujiang'))
ex.post('/upload',(req, res)=>{
    var body='';
    req.on('data', function (d) {
        console.log('aaa');
        body+=d;
    });
    req.on('end', function () {
        body=decode(body);
        console.log(body);
        // body=str2ab(body);
        // console.log('字符串转ArrayBuffer:',body);
        res.send(body);
        //let data= new Uint16Array(body);
        // console.log(data);
        fs.writeFile('input.jpg', body,  'binary',function(err) {
            if (err) {
                return console.error(err);
            }
            console.log("数据写入成功！");
            console.log("--------我是分割线-------------")
            console.log("读取写入的数据！");
            
         });
    });
});

ex.listen(80);