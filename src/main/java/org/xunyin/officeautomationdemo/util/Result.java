package org.xunyin.officeautomationdemo.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
        private Integer code; //1:成功 0：失败
        private String message;
        private Object data;

        public static Result success(){
            return new Result(1," ",null);
        }
        public static Result success(String message, Object data){

            return new Result(1,message,data);
       }

        public static Result success(String message){
            return new Result(1,message,null);
        }
        public static Result success(Object data){
            return new Result(1," ",data);
        }
        public static Result error(String message){
            return new Result(0,message,null);
        }


}
