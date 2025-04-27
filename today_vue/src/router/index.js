import { createRouter, createWebHistory } from "vue-router";
import CommunityList from "@/views/CommunityList.vue";
import RoomDetail from "@/views/RoomDetail.vue";
import ReservationSuccess from "@/views/ReservationSuccess.vue";
import UserCommunityApplications from "@/views/UserCommunityApplications.vue";
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
    },
    {
        path: "/user-application",
        name: "userApplication",
        component: UserCommunityApplications
    },
    {
        path: '/student-reservations',
        name: 'StudentReservations',
        component: () => import('@/views/StudentReservations.vue')
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;


