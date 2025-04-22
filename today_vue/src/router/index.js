import { createRouter, createWebHistory } from "vue-router";
import CommunityList from "@/views/CommunityList.vue";
import RoomDetail from "@/views/RoomDetail.vue";
import ReservationSuccess from "@/views/ReservationSuccess.vue";

const routes = [
    {
        path: "/",
        name: "CommunityList",
        component: CommunityList
    },
    {
        path: "/room/:roomId",
        name: "RoomDetail",
        component: RoomDetail,
        props: true
    },
    {
        path: "/reservation-success",
        name: "ReservationSuccess",
        component: ReservationSuccess
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

// —— 全局路由守卫 —— //
// router.beforeEach((to, from, next) => {
//     // 1. 先从 sessionStorage 里拿 token
//     let token = sessionStorage.getItem("authToken");
//     // 2. 如果没有，再从 URL query（?token=）里取
//     if (!token) {
//         const urlParams = new URL(window.location.href).searchParams;
//         token = urlParams.get("token");
//         if (token) {
//             // 第一次拿到就存起来
//             sessionStorage.setItem("authToken", token);
//             // 并清除地址栏的 token 参数
//             const url = new URL(window.location.href);
//             url.searchParams.delete("token");
//             window.history.replaceState({}, "", url.toString());
//         }
//     }
//
//     // 3. 如果拿到了 token，就放行
//     if (token) {
//         return next();
//     }
//
//     // 4. 如果还是没 token，跳去后端 CAS 登录入口，完成 SSO 流程
//     window.location.href = "http://localhost:8090/user/LoginByCAS";
// });

export default router;
